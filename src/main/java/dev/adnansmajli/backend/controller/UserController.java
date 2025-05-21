package dev.adnansmajli.backend.controller;

import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.mappers.UserMapper;
import dev.adnansmajli.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    // Optional: lock down so only admins see it
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
