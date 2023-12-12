package com.centralstudenthub.controller;


import com.centralstudenthub.Model.Request.LoginRequest;
import com.centralstudenthub.Model.Response.LoginResponse;
import com.centralstudenthub.Model.Request.SignUpRequest;
import com.centralstudenthub.Model.Response.SignUpResponse;
import com.centralstudenthub.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        SignUpResponse signUpResponse = authenticationService.signUp(signUpRequest);
        return ResponseEntity.ok(signUpResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        String token = authenticationService.login(loginRequest);
        if(token != null){
            LoginResponse loginResponse = new LoginResponse(token,true);
            System.out.println(token);
            return ResponseEntity.ok(loginResponse);
        }
        else{
            return ResponseEntity.ok(new LoginResponse("no token",false));
        }
    }

    //@GetMapping("/google/{gmail}")
//    public ResponseEntity<LoginResponse> loginWithGoogle(@PathVariable(value="gmail") String gmail){
//        String token = authenticationService.processOAuthPostLogin(gmail);
//        if(token != null){
//            LoginResponse loginResponse = new LoginResponse(token,true);
//            System.out.println(token);
//            return ResponseEntity.ok(loginResponse);
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new LoginResponse("no token",false));
//        }
//    }

    @GetMapping("/google")
    public String loginWithGoogle(@AuthenticationPrincipal OAuth2User oAuth2User){

        System.out.println(oAuth2User);

//        String token = authenticationService.processOAuthPostLogin(oAuth2User);
//        if(token != null){
//            LoginResponse loginResponse = new LoginResponse(token,true);
//            System.out.println(token);
//            return ResponseEntity.ok(loginResponse);
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new LoginResponse("no token",false));
//        }
        return "login";
    }

}
