package com.centralstudenthub.CentralStudentHub.repository;

import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import com.centralstudenthub.CentralStudentHub.Model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest  //to not save in actual DB
class UserSessionInfoRepositoryTest {

    @Autowired
    private  UserSessionInfoRepository  userSessionInfoRepository;

    /*@Autowired
    private TestEntityManager entityManager;*/

    @BeforeEach
    void setUp() {

        String email = "ali@gmail.com";
        String password = "1234";

        UserAccount userAccount
                = UserAccount.builder()
                .userType(Role.Student)
                .ssn("xx-xxx-xxx-xy")
                .email(email)
                .passwordHash(password)
                .passwordSalt(null)
                .passwordDate(null)
                .build();

        userSessionInfoRepository.save(userAccount);
        //entityManager.persist(userAccountRet);
    }

    @Test
    void existingUser() {
        Optional<UserAccount> userAcc = userSessionInfoRepository.findByEmail("ali@gmail.com");
        assertTrue(userAcc.isPresent());
    }

    @Test
    void nonExistingUser() {
        Optional<UserAccount> userAcc = userSessionInfoRepository.findByEmail("Maro@gmail.com");
        assertFalse(userAcc.isPresent());
    }
}