package dev.adnansmajli.backend.mappers;


import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.dtos.UserRegistrationRequestDto;
import dev.adnansmajli.backend.infrastructure.mapping.SimpleMapper;
import dev.adnansmajli.backend.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends SimpleMapper<User, UserDto> {
    User fromUserRegistrationDto(UserRegistrationRequestDto userRegDto);
}
