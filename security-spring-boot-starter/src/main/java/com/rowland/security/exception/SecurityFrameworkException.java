package com.rowland.security.exception;

public class SecurityFrameworkException extends RuntimeException {
    public SecurityFrameworkException(String message) {
        super(message);
    }

    public SecurityFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}