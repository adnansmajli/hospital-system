// src/main/java/dev/adnansmajli/backend/mappers/UserMapper.java
package dev.adnansmajli.backend.mappers;

import dev.adnansmajli.backend.dtos.RegisterRequestDto;
import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.dtos.UserRegistrationRequestDto;
import dev.adnansmajli.backend.infrastructure.mapping.SimpleMapper;
import dev.adnansmajli.backend.models.User;
import org.mapstruct.Mapper;

/**
 * Maps between User and UserDto, plus a helper from Registration DTO → User entity.
 */

public interface UserMapper extends SimpleMapper<User, UserDto> {
    /**
     * Convert a registration request into a fresh User entity.
     * (Password must still be BCrypt-encoded later in your service.)
     */
    User fromUserRegistrationRequestDto(UserRegistrationRequestDto reg);
    User   toEntity(RegisterRequestDto dto);
}
