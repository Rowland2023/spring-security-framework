package com.rowland.identity.controller;

import com.rowland.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api") // Base changed to /api to match requirements
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    /**
     * Requirement: /api/public/health (public)
     * No @PreAuthorize needed because SecurityConfig permits all on this path.
     */
    @GetMapping("/public/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "message", "Service is healthy"));
    }

    /**
     * Requirement: /api/user/me (requires authentication)
     */
    @GetMapping("/user/me")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> me(Authentication authentication) {
        return Map.of(
            "username", authentication.getName(),
            "roles", authentication.getAuthorities()
        );
    }

    /**
     * Requirement: /api/admin/users (requires ROLE_ADMIN)
     */
    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        // Returns all users from the DB - Only visible to Admins
        return ResponseEntity.ok(userRepository.findAll());
    }
}