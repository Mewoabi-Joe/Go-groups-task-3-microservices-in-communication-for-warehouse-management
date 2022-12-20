package gogroups.warehouseapp.userservice.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import gogroups.warehouseapp.userservice.dto.LoginDto;
import gogroups.warehouseapp.userservice.dto.SignupDto;
import gogroups.warehouseapp.userservice.exceptions.BadRequestException;
import gogroups.warehouseapp.userservice.exceptions.NotFoundException;
import gogroups.warehouseapp.userservice.models.User;
import gogroups.warehouseapp.userservice.repos.UserRepo;
import gogroups.warehouseapp.userservice.responses.AuthResponse;
import gogroups.warehouseapp.userservice.responses.UserResponse;
import gogroups.warehouseapp.userservice.utilities.Utility;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    private String secret = "This secret should be stored securedly in production";
    private Logger logger = LoggerFactory.getLogger(Slf4j.class);

    public User getUserWithIdIfExistOrError(Long userId){
        Optional<User> optionalUser = userRepo.findById(userId);
        if(!optionalUser.isPresent()) throw new NotFoundException("No user exist with that Id");
        return optionalUser.get();
    }

    public AuthResponse signUpUser(SignupDto signupDto) {

        String username = signupDto.getName();
        String password = signupDto.getPassword();
        String email = signupDto.getEmail();

//        if(!Utility.isValidRole(signupDto.getRole())) throw new BadRequestException("That is not a valid role");

        String role = signupDto.getRole() == null ? "ROLE_USER" : signupDto.getRole();

        Optional<User> optionalUser = userRepo.findByNameOrEmail(username, email);
        if(optionalUser.isPresent()){
            throw new BadRequestException("A user already exist with that name or email");
        }

        User savedUser = userRepo.save(new User(username, email, BCrypt.hashpw(password, BCrypt.gensalt()), role));

        String[] tokens = createTokensWithUserIdAndRole(savedUser.getId(), savedUser.getRole());

        return new AuthResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole(), tokens[0], tokens[1]);
    }

    public List<UserResponse> getAllUsers(Long userId, String role) {
        getUserWithIdIfExistOrError(userId);
        if(!(role.equals("ROLE_ADMIN"))) throw new BadRequestException("Only an admin can know all the users");
        List<User> users = userRepo.findAll();
        List<UserResponse> userResponses = users.stream().map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole())).collect(Collectors.toList());
        return userResponses;
    }

    public String[] createTokensWithUserIdAndRole(Long userId, String role){
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());

        String access_token = JWT.create()
                .withSubject(userId.toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .withClaim("role", role )
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(userId.toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withClaim("role", role )
                .sign(algorithm);

        return new String[]{access_token, refresh_token};

    }

    public AuthResponse loginUser(LoginDto loginDto) {
        Optional<User> optionalUser = userRepo.findByEmail(loginDto.getEmail());
        if(!optionalUser.isPresent()) throw new NotFoundException("No user exist with that email");
        User user = optionalUser.get();
        if(!BCrypt.checkpw(loginDto.getPassword(),user.getPassword())) throw new NotFoundException("Enter a correct password for the user");
        String[] tokens = createTokensWithUserIdAndRole(user.getId(), user.getRole());
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(), tokens[0], tokens[1]);

    }

    public AuthResponse refreshToken(Long userId, String role) {
        User user = this.getUserWithIdIfExistOrError(userId);
        String[] tokens = this.createTokensWithUserIdAndRole(user.getId(), user.getRole());
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(), tokens[0], tokens[1]);
    }
}
