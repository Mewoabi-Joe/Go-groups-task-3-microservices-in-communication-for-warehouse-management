package com.learningmanagementsystem.EurekaNamingServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route("user-service", r -> r.path("/users/**","/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))

                .route("inventory-service", r -> r.path("/inventory/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://inventory-service"))

                .route("ui-driven-service", r -> r.path("/ui/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ui-driven-service"))

                .build();
    }

}
