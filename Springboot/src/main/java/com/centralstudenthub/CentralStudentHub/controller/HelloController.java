package com.centralstudenthub.CentralStudentHub.controller;

import com.centralstudenthub.CentralStudentHub.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class HelloController {

    @Autowired
    private JwtService jwtService;
    @GetMapping("/")
    public String Hello(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);

        return jwtService.extractUsername(token);
    }
}
