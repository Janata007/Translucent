package com.example.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        return "User service is taking longer than expected. Please try again later";
    }

    @GetMapping("/sectorServiceFallBack")
    public String sectorServiceFallBackMethod() {
        return "Sector service is taking longer than expected. Please try again later";
    }

}
