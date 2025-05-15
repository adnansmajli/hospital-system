package dev.adnansmajli.backend.service;

import dev.adnansmajli.backend.dtos.LoginRequestDto;
import dev.adnansmajli.backend.models.User;

public interface AuthService {
    /**
     * Authenticates the user and returns a signed JWT.
     */
    String authenticateAndGetToken(LoginRequestDto loginRequest);

    /**
     * Look up the User entity by username (for mapping back to UserDto).
     */
    User getUserDetails(String username);
}
