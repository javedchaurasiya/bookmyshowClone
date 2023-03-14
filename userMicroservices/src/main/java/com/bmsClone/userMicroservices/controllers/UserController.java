package com.bmsClone.userMicroservices.controllers;

import com.bmsClone.userMicroservices.constants.errors;
import com.bmsClone.userMicroservices.error.CustomError;
import com.bmsClone.userMicroservices.models.User;
import com.bmsClone.userMicroservices.models.dtoModels.UserDto;
import com.bmsClone.userMicroservices.models.dtoModels.ResponseDto;
import com.bmsClone.userMicroservices.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final Environment env;

    @GetMapping("/health")
    public String getHealth() {
        return "Port : " + env.getProperty("local.server.port");
    }

    //not in use
//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
//        try {
//            userService.signup(userDto);
//            return ResponseEntity.ok(new ResponseDto(true, "Signup Successful"));
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
//        }
//    }
//
//    //not in use
//    @GetMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
//        try {
//            userService.login(userDto);
//            return ResponseEntity.ok(new ResponseDto(true, "Logged In Successfully"));
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
//        }
//    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<User> getUser(@RequestHeader Map<String, String> headers) {
        return ResponseEntity.ok(userService.getUser(headers.get("id")));
    }
}
