package com.rowland.security.user_management_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/health")
    public Map<String, String> health() {
        // Map.of is an immutable factory method introduced in Java 9
        return Map.of("status", "UP");
    }
}