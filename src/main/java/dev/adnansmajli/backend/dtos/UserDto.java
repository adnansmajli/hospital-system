package dev.adnansmajli.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String username;

    private String name;

    private String surname;

    private String role;

    private String email;

    private String phone;

    private LocalDateTime createdAt;

    private LocalDateTime lastLoginAt;
}
