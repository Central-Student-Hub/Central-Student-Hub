package com.centralstudenthub.CentralStudentHub.controller;

import com.centralstudenthub.CentralStudentHub.Model.LoginRequest;
import com.centralstudenthub.CentralStudentHub.Model.Role;
import com.centralstudenthub.CentralStudentHub.Model.SignUpRequest;
import com.centralstudenthub.CentralStudentHub.Model.SignUpResponse;
import com.centralstudenthub.CentralStudentHub.config.WebSecurityConfig;
import com.centralstudenthub.CentralStudentHub.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(AuthenticationController.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebSecurityConfig.class)
@WebMvcTest(controllers = AuthenticationController.class)
@WebAppConfiguration
class AuthenticationControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private AuthenticationService authenticationService;

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

        String token = "token";

        Mockito.when(authenticationService.login(loginRequest))
                .thenReturn(token);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                        .andExpect(status().isOk());
                        //.andExpect(jsonPath("$.token")
                               // .value(token));
    }

    @Test
    void loginNonExistingUser() throws Exception {

        String email = "ali@gmail.com";
        String password = "1234";
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        Mockito.when(authenticationService.login(loginRequest))
                .thenReturn(null);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
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
                .andExpect(status().isOk());
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
                .andExpect(status().isOk());
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
                .andExpect(status().isOk());
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
                .andExpect(status().isOk());
    }
}