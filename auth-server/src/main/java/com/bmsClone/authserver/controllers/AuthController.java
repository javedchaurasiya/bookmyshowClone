package com.bmsClone.authserver.controllers;

import com.bmsClone.authserver.constants.errors;
import com.bmsClone.authserver.models.dtoModels.ResponseDto;
import com.bmsClone.authserver.models.dtoModels.UserDto;
import com.bmsClone.authserver.services.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(authService.login(userDto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        try {
            authService.signup(userDto);
            return ResponseEntity.ok(new ResponseDto(true, "Signup Successful"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
