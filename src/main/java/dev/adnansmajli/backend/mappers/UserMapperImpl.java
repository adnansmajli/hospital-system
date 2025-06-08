package dev.adnansmajli.backend.mappers;

import dev.adnansmajli.backend.dtos.RegisterRequestDto;
import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.dtos.UserRegistrationRequestDto;
import dev.adnansmajli.backend.models.Role;
import dev.adnansmajli.backend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromUserRegistrationRequestDto(UserRegistrationRequestDto reg) {
        if (reg == null) return null;
        User u = new User();
        u.setUsername(reg.getUsername());
        u.setPassword(reg.getPassword());  // remember to BCrypt this in AuthService
        u.setName(reg.getName());
        u.setSurname(reg.getSurname());
        u.setRole(reg.getRole());
        u.setEmail(reg.getEmail());
        u.setPhone(reg.getPhone());
        return u;
    }

    @Override
    public User toEntity(RegisterRequestDto dto) {
        if (dto == null) {
            return null;
        }
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setName(dto.getName());     // map your “name” field
        u.setSurname(dto.getSurname());  // map “surname”
        u.setRole(dto.getRole());     // map “role”
        u.setEmail(dto.getEmail());
        u.setPhone(dto.getPhone());    // map “phone”
        // leave password out here—your service will encode & set it
        return u;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if (userDto == null) return null;
        User u = new User();
        u.setId(userDto.getId());
        u.setUsername(userDto.getUsername());
        u.setName(userDto.getName());
        u.setSurname(userDto.getSurname());
        u.setRole(Role.valueOf(userDto.getRole()));
        u.setEmail(userDto.getEmail());
        u.setPhone(userDto.getPhone());
        return u;
    }

    @Override
    public UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setRole(String.valueOf(user.getRole()));
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        return dto;
    }
}

