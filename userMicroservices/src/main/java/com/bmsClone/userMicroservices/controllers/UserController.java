package com.bmsClone.userMicroservices.controllers;

import com.bmsClone.userMicroservices.constants.errors;
import com.bmsClone.userMicroservices.error.CustomError;
import com.bmsClone.userMicroservices.models.dtoModels.UserDto;
import com.bmsClone.userMicroservices.models.responseModels.Response;
import com.bmsClone.userMicroservices.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) throws Exception{
        try{
            userService.signup(userDto);
            return ResponseEntity.ok(new Response(true,"Signup Successful"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getUserDetails/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) throws Exception{
        try{
            return ResponseEntity.ok(userService.getUser(id));
        }catch (Exception e){
            if(e instanceof CustomError)return ResponseEntity.status(((CustomError) e).getStatus()).body(new Response(false,e.getMessage()));
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
