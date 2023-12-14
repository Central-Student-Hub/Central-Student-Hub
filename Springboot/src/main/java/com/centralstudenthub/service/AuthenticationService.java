package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.LoginRequest;
import com.centralstudenthub.Model.Request.SignUpRequest;
import com.centralstudenthub.Model.Response.SignUpResponse;
import com.centralstudenthub.Validator.PasswordSecurity;
import com.centralstudenthub.entity.UserAccount;
import com.centralstudenthub.repository.UserSessionInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserSessionInfoRepository userSessionInfoRepository;

    @Autowired
    private PasswordSecurity passwordSecurity;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public SignUpResponse signUp(SignUpRequest signUpRequest){

        Optional<UserAccount> DBUser = userSessionInfoRepository.findBySsn(signUpRequest.getSsn());
        boolean userPresent = DBUser.isPresent();
        if(userPresent && DBUser.get().getEmail() == null){

            Optional<UserAccount> checkEmail = userSessionInfoRepository.findByEmail(signUpRequest.getEmail());
            if(checkEmail.isEmpty()){
                UserAccount user = DBUser.get();
                String salt = passwordSecurity.getNextSalt();
                String hashedPassword = passwordSecurity
                        .hashPassword(signUpRequest.getPassword(),salt);

                user.setUserType(signUpRequest.getUserType());
                user.setEmail(signUpRequest.getEmail());
                user.setPasswordHash(hashedPassword);
                user.setPasswordSalt(salt);
                user.setPasswordDate(new Date(System.currentTimeMillis()));

                userSessionInfoRepository.save(user);
                return new SignUpResponse("Account Created Successfully",true);
            }
            else{
                return new SignUpResponse("Email Already Exists",false);
            }
        }

        if(!userPresent) return new SignUpResponse("You don't have access",false);
        else return new SignUpResponse("You already have an account with this email",false);
    }


    public String login(LoginRequest loginRequest){

        Optional<UserAccount> user = userSessionInfoRepository.findByEmail(loginRequest.getEmail());
        if(user.isEmpty()){
            return null;
        }
        String salt = user.get().getPasswordSalt();
        String pass = loginRequest.getPassword()+salt;

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       loginRequest.getEmail(),
                        pass
                )
        );

        String token = jwtService.generateToken(user.get());

        return token;
    }

    public String processOAuthPostLogin(OAuth2AuthenticationToken authentication) {

        String gmail = ((OAuth2User)authentication.getPrincipal()).getAttribute("email");
        Optional<UserAccount> existUser = userSessionInfoRepository.findByGmail(gmail);
        if (existUser.isEmpty()) return null;

        return jwtService.generateToken(existUser.get());
    }
}
