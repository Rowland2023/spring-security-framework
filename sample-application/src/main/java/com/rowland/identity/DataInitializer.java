package com.rowland.identity;

import com.rowland.identity.repository.UserRepository;
import com.rowland.identity.entity.UserEntity; // Import the Entity name you used
import com.rowland.identity.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            // Use UserEntity here to match the Repository!
            UserEntity admin = new UserEntity(); 
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            
            userRepository.save(admin); // This should now turn GREEN
            System.out.println("✅ Admin user created.");
        }
    }
}