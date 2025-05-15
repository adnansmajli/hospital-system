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

    @Override
    public UserDto register(UserRegistrationRequestDto userRegDto) {
        User user = userMapper.fromUserRegistrationDto(userRegDto);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserNameExistException();
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailExistException();
        }

        //TODO: implement the logic to encrypt/encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //kur e ruan na kthen User-in e ruajtur se bashku me ID-ne e gjeneruar
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);

    }

    @Override
    public UserDto login(String username, String password) {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new WrongPasswordException();
        }
        return userMapper.toDto(user.get());
    }
}










