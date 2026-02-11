package com.rowland.security.user_management_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * This test verifies that the Spring Application Context loads correctly.
 * We use the "test" profile to ensure we use H2 or mock settings instead of 
 * trying to connect to a production PostgreSQL instance.
 */
@SpringBootTest
@ActiveProfiles("test") 
class UserManagementServiceApplicationTests {

    @Test
    void contextLoads() {
        // If the execution reaches this point, it means the 
        // Spring Context (including your Security Starter) initialized correctly.
    }

}