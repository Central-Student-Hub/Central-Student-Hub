package com.centralstudenthub.controller;


import com.centralstudenthub.Model.Request.LoginRequest;
import com.centralstudenthub.Model.Response.LoginResponse;
import com.centralstudenthub.Model.Request.SignUpRequest;
import com.centralstudenthub.Model.Response.SignUpResponse;
import com.centralstudenthub.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ResponseEntity.ok(new LoginResponse("no token",false));
        }
    }
}
