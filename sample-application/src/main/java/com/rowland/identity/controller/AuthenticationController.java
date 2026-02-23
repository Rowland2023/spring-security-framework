package com.rowland.identity.controller; // Ensure this matches your app's package structure

import com.rowland.identity.dto.AuthenticationRequest; // Update with your actual DTO package
import com.rowland.identity.dto.AuthenticationResponse;
import com.rowland.identity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        // This method will now be correctly mapped by Spring DispatcherServlet
        return ResponseEntity.ok(service.authenticate(request));
    }
}