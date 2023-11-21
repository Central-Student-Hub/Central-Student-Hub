package com.centralstudenthub.CentralStudentHub.service;

import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import com.centralstudenthub.CentralStudentHub.entity.UserType;
import com.centralstudenthub.CentralStudentHub.repository.UserSessionInfoRepository;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
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
class SignInServiceTest {

    @Autowired
    private SignInService signInService;

    @MockBean
    private UserSessionInfoRepository userSessionInfoRepository;
    
    @BeforeEach
    void setUp() {

    }

    @Test
    void signInExistingUser() {
        String email = "ali@gmail.com";
        String password = "1234";

        UserAccount userAccountRet
                = UserAccount.builder()
                .userId(1L)
                .userType(UserType.Student)
                .ssn("xx-xxx-xxx-xy")
                .email(email)
                .passwordHash(password)
                .passwordSalt(null)
                .passwordDate(null)
                .build();

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(userAccountRet));

        // TODO: 11/21/2023  We have to create Validator and Validator Mock

        boolean res = signInService.signIn(email,password);
        assertTrue(res);
    }

    @Test
    void signInNonExistingUser() {
        String email = "maro@gmail.com";
        String password = "1234";

        Mockito.when(userSessionInfoRepository.findByEmail(email))
                .thenReturn(null);

        boolean res = signInService.signIn(email,password);
        assertFalse(res);
    }
}