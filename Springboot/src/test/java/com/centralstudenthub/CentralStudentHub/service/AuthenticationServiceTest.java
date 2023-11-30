package com.centralstudenthub.CentralStudentHub.service;

import com.centralstudenthub.CentralStudentHub.Model.LoginRequest;
import com.centralstudenthub.CentralStudentHub.Model.SignUpRequest;
import com.centralstudenthub.CentralStudentHub.Model.SignUpResponse;
import com.centralstudenthub.CentralStudentHub.Validator.PasswordSecurity;
import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import com.centralstudenthub.CentralStudentHub.Model.Role;
import com.centralstudenthub.CentralStudentHub.repository.UserSessionInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private UserSessionInfoRepository userSessionInfoRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordSecurity passwordSecurity;
    
    @BeforeEach
    void setUp() {

    }

    @Test
    void loginExistingUser() {
        String email = "ali@gmail.com";
        String password = "1234";
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
      
        String salt = passwordSecurity.getNextSalt();
        String hashedPassword = passwordSecurity
                .hashPassword(password,salt);

        UserAccount userAccountRet
                = UserAccount.builder()
                .userAccountId(1L)
                .userType(Role.Student)
                .ssn("xx-xxx-xxx-xy")
                .email(email)
                .passwordHash(hashedPassword)
                .passwordSalt(salt)
                .passwordDate(null)
                .build();

        String retToken = jwtService.generateToken(userAccountRet);

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(userAccountRet));

        String token = authenticationService.login(loginRequest);
        assertEquals(retToken,token);
    }

    @Test
    void loginNonExistingUser() {

        String email = "maro@gmail.com";
        String password = "1234";
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(null));

        String res = authenticationService.login(loginRequest);
        assertEquals(null,res);
    }

    @Test
    void signUpValidUser(){
        String SSN = "12345678901234";
        String email = "maro@gmail.com";
        String password = "1234";
        Role type = Role.Student;

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .ssn(SSN)
                .email(email)
                .password(password)
                .userType(type)
                .build();

        UserAccount userAccountRet
                = UserAccount.builder()
                .userAccountId(1L)
                .userType(null)
                .ssn(SSN)
                .email(null)
                .passwordHash(null)
                .passwordSalt(null)
                .passwordDate(null)
                .build();

        Mockito.when(userSessionInfoRepository.findBySsn(SSN))
                .thenReturn(Optional.ofNullable(userAccountRet));

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(null));

        String message = "Account Created Successfully";
        boolean acc = true;
        SignUpResponse signUpResponse = new SignUpResponse(message,acc);

        SignUpResponse res = authenticationService.signUp(signUpRequest);
        assertEquals(signUpResponse,res);
    }

    @Test
    void signUpUserUsingAnAlreadyUsedEmail(){
        String SSN = "12345678901234";
        String email = "maro@gmail.com";
        String password = "1234";
        Role type = Role.Student;

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .ssn(SSN)
                .email(email)
                .password(password)
                .userType(type)
                .build();

        UserAccount userAccountRet
                = UserAccount.builder()
                .userAccountId(1L)
                .userType(null)
                .ssn(SSN)
                .email(null)
                .passwordHash(null)
                .passwordSalt(null)
                .passwordDate(null)
                .build();

        UserAccount checkEmail
                = UserAccount.builder()
                .userAccountId(2L)
                .userType(Role.Student)
                .ssn("12345678901235")
                .email("maro@gmail.com")
                .passwordHash("abc")
                .passwordSalt("abc")
                .passwordDate(new Date(System.currentTimeMillis()))
                .build();

        Mockito.when(userSessionInfoRepository.findBySsn(SSN))
                .thenReturn(Optional.ofNullable(userAccountRet));

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(checkEmail));

        String message = "Email Already Exists";
        boolean acc = false;
        SignUpResponse signUpResponse = new SignUpResponse(message,acc);

        SignUpResponse res = authenticationService.signUp(signUpRequest);
        assertEquals(signUpResponse,res);
    }

    @Test
    void signUpUserHavingNoAccess(){
        String SSN = "12345678901234";
        String email = "maro@gmail.com";
        String password = "1234";
        Role type = Role.Student;

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .ssn(SSN)
                .email(email)
                .password(password)
                .userType(type)
                .build();

        Mockito.when(userSessionInfoRepository.findBySsn(SSN))
                .thenReturn(Optional.ofNullable(null));

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(null));

        String message = "You don't have access";
        boolean acc = false;
        SignUpResponse signUpResponse = new SignUpResponse(message,acc);

        SignUpResponse res = authenticationService.signUp(signUpRequest);
        assertEquals(signUpResponse,res);
    }

    @Test
    void signUpUserHavingAnAccountWithGivenEmail(){
        String SSN = "12345678901234";
        String email = "maro@gmail.com";
        String password = "1234";
        Role type = Role.Student;

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .ssn(SSN)
                .email(email)
                .password(password)
                .userType(type)
                .build();

        UserAccount userAccountRet
                = UserAccount.builder()
                .userAccountId(1L)
                .userType(Role.Student)
                .ssn(SSN)
                .email("maro@gmail.com")
                .passwordHash("abc")
                .passwordSalt("abc")
                .passwordDate(new Date(System.currentTimeMillis()))
                .build();

        Mockito.when(userSessionInfoRepository.findBySsn(SSN))
                .thenReturn(Optional.ofNullable(userAccountRet));

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(userAccountRet));

        String message = "You already have an account with this email";
        boolean acc = false;
        SignUpResponse signUpResponse = new SignUpResponse(message,acc);

        SignUpResponse res = authenticationService.signUp(signUpRequest);
        assertEquals(signUpResponse,res);
    }
}