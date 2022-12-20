package com.learningmanagementsystem.EurekaNamingServer;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtUtil {

    private String secret = "This secret should be stored securedly in production";

    private boolean isTokenExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(new Date());
    }

    public String[] getUserIdAndRoleFromToken(String passedToken){
        String token = passedToken.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String userId = decodedJWT.getSubject();
        String rawRoleString = String.valueOf(decodedJWT.getClaim("role"));
        String role = rawRoleString.substring(1,rawRoleString.length()-1);
        return new String[]{userId, role};
    }

    public boolean isInvalid(String passedToken) {
        boolean isInvalid = false;
        DecodedJWT decodedJWT;
        try {
            String token = passedToken.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            decodedJWT = verifier.verify(token);
            Long userId = Long.valueOf(decodedJWT.getSubject());
            String role = String.valueOf(decodedJWT.getClaim("role"));
        }catch (Exception exception){
            isInvalid = true;
            return true;
        }
        return this.isTokenExpired(decodedJWT);
    }
}
