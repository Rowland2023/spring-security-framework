package com.rowland.security.user_management_service.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j // Lombok annotation for private static final Logger log
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
            
        // Proceed with the filter chain first so that the JWT filter 
        // has a chance to populate the SecurityContext
        chain.doFilter(request, response);

        // Post-processing log: Now we know WHO the user is
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            log.info("User: {} | Method: {} | URI: {} | Status: {}", 
                auth.getName(), 
                request.getMethod(), 
                request.getRequestURI(),
                response.getStatus());
        } else {
            log.info("Anonymous access | Method: {} | URI: {}", 
                request.getMethod(), 
                request.getRequestURI());
        }
    }
}