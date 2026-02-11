package com.rowland.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// We MUST tell Spring to look in BOTH the identity app and the security library
@ComponentScan(basePackages = {
    "com.rowland.identity", 
    "com.rowland.security"
})
public class UserManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
}