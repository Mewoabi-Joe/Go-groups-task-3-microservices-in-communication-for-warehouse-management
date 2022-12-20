package com.learningmanagementsystem.EurekaNamingServer;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    public static final List<String> openApiEndpoints= List.of(
            "/auth/signup",
            "/auth/login"
    );

    // Even the endpoints /refresh and /user have the tokens in their header which have to validated and mapped.

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
