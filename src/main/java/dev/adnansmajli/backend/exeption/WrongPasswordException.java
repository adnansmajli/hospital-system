package dev.adnansmajli.backend.exeption;
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException() {
        super("Wrong password");
    }
}
