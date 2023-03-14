package com.bmsClone.authserver.controllers;

import com.bmsClone.authserver.constants.errors;
import com.bmsClone.authserver.models.dtoModels.JwtDto;
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
    public ResponseEntity<JwtDto> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.login(userDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody UserDto userDto) {
        authService.signup(userDto);
        return ResponseEntity.ok(new ResponseDto(true, "Signup Successful"));
    }
}
