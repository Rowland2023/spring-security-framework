package com.rowland.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import com.rowland.security.jwt.JwtService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    // This nested class tells Spring Boot how to load the context for this library test
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @Import(JwtService.class) // Manually import the service we want to test
    static class TestConfig {}

    @Test
    void shouldGenerateAndExtractTokenWithRoles() {
        // 1. Create a dummy user
        UserDetails user = new User("admin@test.com", "password", 
            List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        // 2. Generate token
        String token = jwtService.generateToken(user);

        // 3. Extract and Verify Username
        String extractedUsername = jwtService.extractUsername(token);
        
        // 4. Verify Token Validity
        boolean isValid = jwtService.isTokenValid(token, user);

        assertAll("JWT Validation",
            () -> assertNotNull(token, "Token should not be null"),
            () -> assertEquals("admin@test.com", extractedUsername, "Usernames should match"),
            () -> assertTrue(isValid, "Token should be valid for the generated user")
        );
    }
}