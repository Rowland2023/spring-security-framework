package com.rowland.identity;

import com.rowland.identity.entity.UserEntity;
import com.rowland.identity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

// The scanBasePackages tells Spring to look for @Component and @Configuration 
// in both the identity and security folders.
@SpringBootApplication(scanBasePackages = "com.rowland")
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

    /**
     * This "Bootstrap" bean runs at startup. 
     * It checks if an admin exists; if not, it creates one.
     */
    @Bean
    CommandLineRunner bootstrap(UserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.findByUsername("admin").isEmpty()) {
                UserEntity admin = UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123")) 
                        .role("ROLE_ADMIN")
                        .build();
                repository.save(admin);
                System.out.println("✅ Default Admin created: admin/admin123");
            } else {
                System.out.println("ℹ️ Admin user already exists in database.");
            }
        };
    }
}