package com.rowland.security;

import com.rowland.security.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        // No @SpringBootTest needed! Just standard Java.
        jwtService = new JwtService(); 
    }

    @Test
    void shouldGenerateAndExtractToken() {
        UserDetails user = new User("testUser", "password", new ArrayList<>());
        String token = jwtService.generateToken(user);
        assertNotNull(token);
        assertEquals("testUser", jwtService.extractUsername(token));
    }
}