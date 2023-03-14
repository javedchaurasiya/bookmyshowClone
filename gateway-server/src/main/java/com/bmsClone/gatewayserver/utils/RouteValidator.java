package com.bmsClone.gatewayserver.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
@Data
@RequiredArgsConstructor
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/auth/signup",
            "/auth/login"
    );
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
