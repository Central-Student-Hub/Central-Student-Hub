package com.centralstudenthub.CentralStudentHub.service;

import com.centralstudenthub.CentralStudentHub.Model.LoginRequest;
import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import com.centralstudenthub.CentralStudentHub.Model.Role;
import com.centralstudenthub.CentralStudentHub.repository.UserSessionInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private UserSessionInfoRepository userSessionInfoRepository;
    
    @BeforeEach
    void setUp() {

    }

    @Test
    void signInExistingUser() {
        String email = "ali@gmail.com";
        String password = "1234";
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        UserAccount userAccountRet
                = UserAccount.builder()
                .userAccountId(1L)
                .userType(Role.Student)
                .ssn("xx-xxx-xxx-xy")
                .email(email)
                .passwordHash(password)
                .passwordSalt(null)
                .passwordDate(null)
                .build();

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(userAccountRet));

        // TODO: 11/21/2023  We have to create Validator and Validator Mock

        String res = authenticationService.login(loginRequest);
        assertTrue(Boolean.parseBoolean(res));
    }

    @Test
    void signInNonExistingUser() {

        String email = "maro@gmail.com";
        String password = "1234";
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(null);

        String res = authenticationService.login(loginRequest);
        assertFalse(Boolean.parseBoolean(res));
    }
}