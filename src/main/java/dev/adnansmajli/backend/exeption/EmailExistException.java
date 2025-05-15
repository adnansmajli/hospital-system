package dev.adnansmajli.backend.exeption;

import jakarta.persistence.EntityExistsException;

public class EmailExistException extends EntityExistsException {
    public EmailExistException(String message) {
        super(message);
    }

    public EmailExistException() {
        super("Email already exists");
    }
}
