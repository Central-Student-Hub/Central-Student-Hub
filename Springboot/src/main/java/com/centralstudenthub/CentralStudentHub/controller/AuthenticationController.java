package com.centralstudenthub.CentralStudentHub.controller;


import com.centralstudenthub.CentralStudentHub.Model.LoginRequest;
import com.centralstudenthub.CentralStudentHub.Model.LoginResponse;
import com.centralstudenthub.CentralStudentHub.Model.SignUpRequest;
import com.centralstudenthub.CentralStudentHub.Model.SignUpResponse;
import com.centralstudenthub.CentralStudentHub.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private AuthenticationService signinService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        SignUpResponse signUpResponse = signinService.signUp(signUpRequest);
        return ResponseEntity.ok(signUpResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        String token = signinService.login(loginRequest);
        if(token != null){
            LoginResponse loginResponse = new LoginResponse(token,true);
            System.out.println(token);
            return ResponseEntity.ok(loginResponse);
        }
        else{
            return ResponseEntity.ok(new LoginResponse("no Token",false));
        }
    }
}
