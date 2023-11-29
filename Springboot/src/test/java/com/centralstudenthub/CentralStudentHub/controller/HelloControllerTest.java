package com.centralstudenthub.CentralStudentHub.controller;

import com.centralstudenthub.CentralStudentHub.Model.LoginRequest;
import com.centralstudenthub.CentralStudentHub.Model.Role;
import com.centralstudenthub.CentralStudentHub.Validator.PasswordSecurity;
import com.centralstudenthub.CentralStudentHub.config.WebSecurityConfig;
import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import com.centralstudenthub.CentralStudentHub.repository.UserSessionInfoRepository;
import com.centralstudenthub.CentralStudentHub.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WebSecurityConfig.class })
@WebMvcTest(controllers = HelloController.class)
@WebAppConfiguration
@ComponentScan(basePackages = "com.centralstudenthub.CentralStudentHub")
class HelloControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JwtService jwtService;

    @MockBean
    private UserSessionInfoRepository userSessionInfoRepository;

    @Autowired
    private PasswordSecurity passwordSecurity;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void invalidTokenIncorrectFormat() throws Exception {
        String invalidTokenIncorrectFormat = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        mockMvc.perform(get("/Hello")
                .header("Authorization", invalidTokenIncorrectFormat))
                .andExpect(status().isForbidden());
    }

    @Test
    void invalidTokenCorrectFormat() throws Exception {
        String invalidTokenCorrectFormat = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        assertThrows(SignatureException.class, () -> {
            mockMvc.perform(get("/Hello")
                    .header("Authorization", invalidTokenCorrectFormat))
                    .andExpect(status().isForbidden());
        });
    }

    @Test
    void validTokenIncorrectFormat() throws Exception {
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

        String validTokenIncorrectFormat = jwtService.generateToken(userAccountRet);

        mockMvc.perform(get("/Hello")
                .header("Authorization", validTokenIncorrectFormat))
                .andExpect(status().isForbidden());
    }

    @Test
    void validTokenCorrectFormat() throws Exception {
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

        String validTokenCorrectFormat = "Bearer " + jwtService.generateToken(userAccountRet);

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(userAccountRet));

        mockMvc.perform(get("/Hello")
                .header("Authorization", validTokenCorrectFormat))
                .andExpect(status().isOk());
    }
}
