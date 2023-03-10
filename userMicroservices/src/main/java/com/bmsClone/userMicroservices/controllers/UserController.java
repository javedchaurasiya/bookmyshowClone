package com.bmsClone.userMicroservices.controllers;

import com.bmsClone.userMicroservices.constants.errors;
import com.bmsClone.userMicroservices.error.CustomError;
import com.bmsClone.userMicroservices.models.dtoModels.UserDto;
import com.bmsClone.userMicroservices.models.dtoModels.ResponseDto;
import com.bmsClone.userMicroservices.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Environment env;

    @GetMapping("/health")
    public String getHealth() {
        return "Port : " + env.getProperty("local.server.port");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        try {
            userService.signup(userDto);
            return ResponseEntity.ok(new ResponseDto(true, "Signup Successful"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getUserDetails/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (Exception e) {
            if (e instanceof CustomError)
                return ResponseEntity.status(((CustomError) e).getStatus()).body(new ResponseDto(false, e.getMessage()));
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
