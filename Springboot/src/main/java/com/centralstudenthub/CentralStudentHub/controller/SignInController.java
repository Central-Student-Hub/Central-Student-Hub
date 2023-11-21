package com.centralstudenthub.CentralStudentHub.controller;


import com.centralstudenthub.CentralStudentHub.service.SignInService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignInController {

    @Autowired
    SignInService signinService;
    @PostMapping("/signIn")
    public boolean signIn(@RequestParam String email, @RequestParam String password, HttpServletResponse response){
        signinService.signIn(email,password);
        return true;
    }
}
