// src/main/java/dev/adnansmajli/backend/controller/AuthController.java
package dev.adnansmajli.backend.controller;

import dev.adnansmajli.backend.dtos.JwtResponseDto;
import dev.adnansmajli.backend.dtos.LoginRequestDto;
import dev.adnansmajli.backend.dtos.RegisterRequestDto;
import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.mappers.UserMapper;
import dev.adnansmajli.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserMapper mapper;


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @Valid @RequestBody RegisterRequestDto signUpRequest
    ) throws Exception {
        UserDto created = authService.register(signUpRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto creds) {
        String token = authService.authenticateAndGetToken(creds);
        UserDto user = mapper.toDto(authService.getUserDetails(creds.getUsername()));
        return ResponseEntity.ok(new JwtResponseDto(token, user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(Authentication authentication) {
        String username = authentication.getName();
        UserDto user = mapper.toDto(authService.getUserDetails(username));
        return ResponseEntity.ok(user);
    }

}
