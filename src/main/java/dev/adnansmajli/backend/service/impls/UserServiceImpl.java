package dev.adnansmajli.backend.service.impls;

import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.dtos.UserRegistrationRequestDto;
import dev.adnansmajli.backend.exeption.EmailExistException;
import dev.adnansmajli.backend.exeption.UserNameExistException;
import dev.adnansmajli.backend.exeption.UserNotFoundException;
import dev.adnansmajli.backend.exeption.WrongPasswordException;
import dev.adnansmajli.backend.mappers.UserMapper;
import dev.adnansmajli.backend.models.User;
import dev.adnansmajli.backend.repositories.UserRepository;
import dev.adnansmajli.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /* ---------- REGISTER ---------- */
    @Override
    public UserDto register(UserRegistrationRequestDto regDto) {

        /* validime … */

        User user = userMapper.fromUserRegistrationRequestDto(regDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // createdAt u vendos në mapper, lastLoginAt mbetet null
        User saved = userRepository.save(user);

        return userMapper.toDto(saved);
    }

    /* ---------- LOGIN ---------- */
    @Override
    public UserDto login(String username, String rawPassword) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new WrongPasswordException();
        }

        /* ★ përditëso lastLoginAt */
        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}










