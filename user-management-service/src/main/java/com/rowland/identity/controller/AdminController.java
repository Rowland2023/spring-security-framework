package com.rowland.identity.controller; // Standard clean package name

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Map<String, String>> users() {
        return List.of(
            Map.of("id", "1", "username", "alice"),
            Map.of("id", "2", "username", "bob")
        );
    }
}