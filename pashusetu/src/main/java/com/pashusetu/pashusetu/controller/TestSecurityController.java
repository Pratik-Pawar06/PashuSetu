package com.pashusetu.pashusetu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    @GetMapping("/api/test/farmer")
    public String farmerAccess() {
        return "Farmer access granted";
    }

    @GetMapping("/api/test/veterinarian")
    public String veterinarianAccess() {
        return "Veterinarian access granted";
    }

    @GetMapping("/api/test/admin")
    public String adminAccess() {
        return "Admin access granted";
    }
}