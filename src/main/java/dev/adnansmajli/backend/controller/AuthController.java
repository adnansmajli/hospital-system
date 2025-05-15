// src/main/java/dev/adnansmajli/backend/controller/AuthController.java
package dev.adnansmajli.backend.controller;

import dev.adnansmajli.backend.dtos.LoginRequestDto;
import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.dtos.JwtResponseDto;
import dev.adnansmajli.backend.mappers.UserMapper;
import dev.adnansmajli.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto creds) {
        String token = authService.authenticateAndGetToken(creds);
        UserDto user = mapper.toDto(authService.getUserDetails(creds.getUsername()));
        return ResponseEntity.ok(new JwtResponseDto(token, user));
    }
}
