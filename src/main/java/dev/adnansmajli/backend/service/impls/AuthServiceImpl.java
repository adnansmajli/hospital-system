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
        // This returns Authentication, not void:
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

//        // (optional) put it in SecurityContext if you need it later
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Pass the Authentication into your new token method:
        return jwtUtils.generateToken(authentication);
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