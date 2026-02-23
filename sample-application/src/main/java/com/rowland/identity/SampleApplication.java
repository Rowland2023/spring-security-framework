package com.rowland.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.rowland.identity", 
    "com.rowland.security"
})
public class SampleApplication { // Renamed from UserManagementApplication
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}