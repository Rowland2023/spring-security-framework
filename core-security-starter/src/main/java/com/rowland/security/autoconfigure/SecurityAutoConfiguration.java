package com.rowland.security.autoconfigure;

import com.rowland.security.jwt.JwtAuthenticationFilter;
import com.rowland.security.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityAutoConfiguration {

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtService jwtService, 
            @Lazy UserDetailsService userDetailsService) {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }
    
    // Note: PasswordEncoder and AuthenticationProvider have been moved 
    // to ApplicationConfig.java to prevent duplicate bean conflicts.
}