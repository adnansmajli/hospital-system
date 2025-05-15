package dev.adnansmajli.backend.service;


import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.dtos.UserRegistrationRequestDto;

public interface UserService {
    UserDto register(UserRegistrationRequestDto userRegDto);

    UserDto login(String username, String password);

}
