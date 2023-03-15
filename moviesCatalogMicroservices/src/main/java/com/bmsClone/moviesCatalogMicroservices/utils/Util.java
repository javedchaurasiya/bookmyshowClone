package com.bmsClone.moviesCatalogMicroservices.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Component
@Data
@RequiredArgsConstructor
public class Util {
    public void throwErrorIfNotAdmin(Map<String, String> headers) {
        if (!(Boolean.getBoolean(headers.getOrDefault("admin", "false"))))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
