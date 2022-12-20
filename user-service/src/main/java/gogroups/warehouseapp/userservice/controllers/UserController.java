package gogroups.warehouseapp.userservice.controllers;

import gogroups.warehouseapp.userservice.dto.LoginDto;
import gogroups.warehouseapp.userservice.dto.SignupDto;
import gogroups.warehouseapp.userservice.responses.AuthResponse;
import gogroups.warehouseapp.userservice.responses.UserResponse;
import gogroups.warehouseapp.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<AuthResponse> signUp (@Valid @RequestBody SignupDto signupDto){
        return new  ResponseEntity<>(userService.signUpUser(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto loginDto){
        return new  ResponseEntity<>(userService.loginUser(loginDto), HttpStatus.OK);
    }

    @GetMapping("/auth/refresh")
    public ResponseEntity<AuthResponse> refresh(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role){
        return new  ResponseEntity<>(userService.refreshToken(userId, role), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers (
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role){
        return new  ResponseEntity<>(userService.getAllUsers(userId, role), HttpStatus.OK);
    }

}
