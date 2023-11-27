package com.centralstudenthub.CentralStudentHub.controller;

import com.centralstudenthub.CentralStudentHub.config.WebSecurityConfig;
import com.centralstudenthub.CentralStudentHub.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void signInExistingUser() throws Exception {

        String email = "ali@gmail.com";
        String password = "1234";

        Mockito.when(authenticationService.login(email,password))
                .thenReturn("true");

        mockMvc.perform(post("/login")
                .param("email",email)
                .param("password",password))
                .andExpect(status().isOk());
    }

    @Test
    void signInNonExistingUser() throws Exception {

        String email = "ali@gmail.com";
        String password = "1234";

        Mockito.when(authenticationService.login(email,password))
                .thenReturn("false");

        mockMvc.perform(post("/login")
                        .param("email",email)
                        .param("password",password))
                .andExpect(status().isOk());
    }
}