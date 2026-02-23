package com.rowland.identity.service;

import com.rowland.identity.dto.AuthenticationRequest;
import com.rowland.identity.dto.AuthenticationResponse;
import com.rowland.identity.repository.UserRepository;
import com.rowland.security.jwt.JwtService; // Adjust package if different in your starter
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // This verifies the username and password against the database
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        // If authentication didn't throw an exception, the user is valid
        var user = repository.findByUsername(request.username())
                .orElseThrow();

        // Generate the token using the starter library
        var jwtToken = jwtService.generateToken(user);
        
        return new AuthenticationResponse(jwtToken);
    }
}