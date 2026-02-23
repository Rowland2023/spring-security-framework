package com.rowland.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Senior Developer Note: This is a cross-cutting concern.
 * It provides a unified error format for all modules consuming this starter.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles 403 Forbidden - e.g., USER trying to access /api/admin/**
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        return createResponseEntity(
                HttpStatus.FORBIDDEN, 
                "Access Denied", 
                "Insufficient permissions to access this resource.", 
                request
        );
    }

    // Handles 401 Unauthorized - e.g., Bad JWT or missing credentials
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentication(AuthenticationException ex, WebRequest request) {
        return createResponseEntity(
                HttpStatus.UNAUTHORIZED, 
                "Unauthorized", 
                ex.getMessage(), 
                request
        );
    }

    // Generic fallback for any other unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllOtherExceptions(Exception ex, WebRequest request) {
        return createResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Internal Server Error", 
                "An unexpected error occurred. Please contact support.", 
                request
        );
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(
            HttpStatus status, String error, String message, WebRequest request) {
        
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(error)
                .message(message)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(response, status);
    }
}