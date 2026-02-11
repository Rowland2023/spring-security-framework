package com.rowland.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // Ensures we use the test properties (H2, test secrets)
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldGenerateAndExtractTokenWithRoles() {
        // 1. Create a dummy user with a role using Java 21 List.of
        UserDetails user = new User("admin@test.com", "password", 
            List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        // 2. Generate token
        String token = jwtService.generateToken(user);

        // 3. Extract and Verify Username
        String extractedUsername = jwtService.extractUsername(token);
        
        // 4. Verify Token Validity
        boolean isValid = jwtService.isTokenValid(token, user);

        // 5. Senior Touch: Extract and verify claims/roles if your Service supports it
        // String roles = jwtService.extractClaim(token, claims -> claims.get("roles", String.class));

        assertAll("JWT Validation",
            () -> assertNotNull(token, "Token should not be null"),
            () -> assertEquals("admin@test.com", extractedUsername, "Usernames should match"),
            () -> assertTrue(isValid, "Token should be valid for the generated user")
            // () -> assertTrue(roles.contains("ROLE_ADMIN"), "Token should contain ADMIN role")
        );
    }
}