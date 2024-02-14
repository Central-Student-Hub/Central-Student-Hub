package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Role;
import com.centralstudenthub.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class RolesController {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/role")
    public String getComponents(HttpServletRequest request) {
        String role = jwtService.extractRole(jwtService.token(request));
        return role;
    }
}
