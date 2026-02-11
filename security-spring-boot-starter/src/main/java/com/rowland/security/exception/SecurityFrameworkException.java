package com.rowland.security.exception;

/**
 * Custom runtime exception for the Security Framework Starter.
 * Used for wrapping JWT issues, configuration errors, and authentication failures.
 */
public class SecurityFrameworkException extends RuntimeException {
    
    public SecurityFrameworkException(String message) {
        super(message);
    }

    public SecurityFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}