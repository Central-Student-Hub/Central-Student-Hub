package com.centralstudenthub.controller;

import com.centralstudenthub.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:3000",allowCredentials = "true",allowedHeaders = {"Authorization"})
public class HelloController {
    private final JwtService jwtService;

    @Autowired
    public HelloController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/Hello")
    public String Hello() {
        return "Hello, Secured!";
    }
}
