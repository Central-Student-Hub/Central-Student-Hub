package com.centralstudenthub.CentralStudentHub.controller;


import com.centralstudenthub.CentralStudentHub.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService signinService;

    @PostMapping("/signUp")
    public boolean signUp(@RequestParam String email, @RequestParam String password){
        signinService.signUp(email,password);
        return true;
    }
    @PostMapping("/login")
    public boolean login(@RequestParam String email, @RequestParam String password){
        signinService.login(email,password);
        return true;
    }
}
