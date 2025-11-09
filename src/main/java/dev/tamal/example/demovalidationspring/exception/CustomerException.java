package dev.tamal.example.demovalidationspring.exception;

import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException {
    private final String code;
    public CustomerException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
