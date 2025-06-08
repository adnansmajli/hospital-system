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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;      // ← add
    private final UserMapper userMapper;


    @Override
    public String authenticateAndGetToken(LoginRequestDto loginRequest) {
        // throws if auth fails
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        return jwtUtils.generateToken(loginRequest.getUsername());
    }

    @Override
    public User getUserDetails(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
    }

    @Override
    public UserDto register(RegisterRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}