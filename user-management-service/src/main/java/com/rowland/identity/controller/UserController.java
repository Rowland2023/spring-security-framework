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
@RequestMapping("/api/v1") // Using v1 to match standard API structures
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    /**
     * Requirement: /api/v1/public/health
     */
    @GetMapping("/public/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "message", "Service is healthy"));
    }

    /**
     * Requirement: /api/v1/user/me
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
     * NOTE: The conflicting /admin/users was removed from here.
     * Admin functionality should stay in AdminController.
     */
}