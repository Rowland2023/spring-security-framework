package com.rowland.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldGenerateAndExtractTokenWithRoles() {
        // 1. Create a dummy user with a role
        UserDetails user = new User("admin@test.com", "password", 
            List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        // 2. Generate token (This uses your updated logic with Extra Claims)
        String token = jwtService.generateToken(user);

        // 3. Extract and Verify
        String extractedUsername = jwtService.extractUsername(token);
        
        assertNotNull(token);
        assertEquals("admin@test.com", extractedUsername);
    }
}