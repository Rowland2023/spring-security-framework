package com.rowland.identity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = SampleApplication.class) // Explicitly point to the new name
@ActiveProfiles("test") 
class SampleApplicationTests {

    @Test
    void contextLoads() {
    }
}