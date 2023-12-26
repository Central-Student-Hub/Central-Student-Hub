package com.centralstudenthub.controller;

import com.centralstudenthub.Model.*;
import com.centralstudenthub.Model.Request.LoginRequest;
import com.centralstudenthub.Model.Request.SignUpRequest;
import com.centralstudenthub.Model.Response.LoginResponse;
import com.centralstudenthub.Model.Response.SignUpResponse;
import com.centralstudenthub.config.WebSecurityConfig;
import com.centralstudenthub.repository.UserSessionInfoRepository;
import com.centralstudenthub.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WebSecurityConfig.class })
@WebMvcTest(controllers = AuthenticationController.class)
@WebAppConfiguration
@ComponentScan(basePackages = "com.centralstudenthub")
class AuthenticationControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private UserSessionInfoRepository userSessionInfoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void loginExistingUser() throws Exception {

        String email = "ali@gmail.com";
        String password = "1234";
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        LoginResponse response = new LoginResponse("token", true);

        Mockito.when(authenticationService.login(loginRequest))
                .thenReturn("token");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void loginNonExistingUser() throws Exception {

        String email = "ali@gmail.com";
        String password = "1234";
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        LoginResponse response = new LoginResponse("no token", false);

        Mockito.when(authenticationService.login(loginRequest))
                .thenReturn(null);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void signUpValidUser() throws Exception {
        String SSN = "12345678901234";
        String email = "maro@gmail.com";
        String password = "1234";
        Role type = Role.Student;

        String message = "Account Created Successfully";
        boolean acc = true;
        SignUpResponse signUpResponse = new SignUpResponse(message,acc);

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .ssn(SSN)
                .email(email)
                .password(password)
                .userType(type)
                .build();

        Mockito.when(authenticationService.signUp(signUpRequest))
                .thenReturn(signUpResponse);

        mockMvc.perform(post("/auth/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(signUpResponse)));
    }

    @Test
    void signUpUserUsingAnAlreadyUsedEmail() throws Exception {
        String SSN = "12345678901234";
        String email = "maro@gmail.com";
        String password = "1234";
        Role type = Role.Student;

        String message = "Email is Used";
        boolean acc = false;
        SignUpResponse signUpResponse = new SignUpResponse(message,acc);

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .ssn(SSN)
                .email(email)
                .password(password)
                .userType(type)
                .build();

        Mockito.when(authenticationService.signUp(signUpRequest))
                .thenReturn(signUpResponse);

        mockMvc.perform(post("/auth/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(signUpResponse)));
    }

    @Test
    void signUpUserHavingNoAccess() throws Exception {
        String SSN = "12345678901200";
        String email = "maro@gmail.com";
        String password = "1234";
        Role type = Role.Student;

        String message = "You don't have access";
        boolean acc = false;
        SignUpResponse signUpResponse = new SignUpResponse(message,acc);

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .ssn(SSN)
                .email(email)
                .password(password)
                .userType(type)
                .build();

        Mockito.when(authenticationService.signUp(signUpRequest))
                .thenReturn(signUpResponse);

        mockMvc.perform(post("/auth/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(signUpResponse)));
    }

    @Test
    void signUpUserHavingAnAccountWithGivenEmail() throws Exception {
        String SSN = "12345678901200";
        String email = "maro@gmail.com";
        String password = "1234";
        Role type = Role.Student;

        String message = "You don't have access";
        boolean acc = false;
        SignUpResponse signUpResponse = new SignUpResponse(message,acc);

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .ssn(SSN)
                .email(email)
                .password(password)
                .userType(type)
                .build();

        Mockito.when(authenticationService.signUp(signUpRequest))
                .thenReturn(signUpResponse);

        mockMvc.perform(post("/auth/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(signUpResponse)));
    }
}