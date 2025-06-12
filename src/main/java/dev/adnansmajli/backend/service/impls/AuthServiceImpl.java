package dev.adnansmajli.backend.service.impls;

import dev.adnansmajli.backend.dtos.LoginRequestDto;
import dev.adnansmajli.backend.dtos.RegisterRequestDto;
import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.mappers.UserMapper;
import dev.adnansmajli.backend.models.Role;
import dev.adnansmajli.backend.models.User;
import dev.adnansmajli.backend.repositories.UserRepository;
import dev.adnansmajli.backend.security.JwtUtils;
import dev.adnansmajli.backend.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /* ------------------------------------------------------------------ */
    /*  LOGIN                                                             */
    /* ------------------------------------------------------------------ */
    @Override
    public String authenticateAndGetToken(LoginRequestDto loginRequest) {

        // 1) krijo Authentication (për Spring Security)
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        // 2) përditëso lastLoginAt
        User user = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow();                       // duhet ekzistojë në këtë pikë

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);                   // RUANI ndryshimin

        // 3) krijo JWT me objektin Authentication
        return jwtUtils.generateToken(authentication);
    }

    /* ------------------------------------------------------------------ */
    /*  REGISTER                                                          */
    /* ------------------------------------------------------------------ */
    @Override
    public UserDto register(RegisterRequestDto req) {

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setName(req.getName());
        user.setSurname(req.getSurname());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setRole(req.getRole() != null ? req.getRole() : Role.USER);

        user.setCreatedAt(LocalDateTime.now());      // ➜ krijimi
        user.setLastLoginAt(null);                   // do plotësohet në login-in e parë

        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    /* ------------------------------------------------------------------ */
    /*  HELPERS                                                           */
    /* ------------------------------------------------------------------ */
    @Override
    public User getUserDetails(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found: " + username));
    }
}
