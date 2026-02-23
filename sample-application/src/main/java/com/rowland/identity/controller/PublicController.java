package com.rowland.identity.controller; // Changed from security.user_management_service to identity

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/test")
    public String test() { return "I am alive"; }
}