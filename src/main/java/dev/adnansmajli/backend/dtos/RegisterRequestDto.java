package dev.adnansmajli.backend.dtos;

import dev.adnansmajli.backend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Role role = Role.USER;
}