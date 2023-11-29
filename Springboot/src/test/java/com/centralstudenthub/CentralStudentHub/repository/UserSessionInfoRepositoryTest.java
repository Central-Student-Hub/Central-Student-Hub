package com.centralstudenthub.CentralStudentHub.repository;

import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import com.centralstudenthub.CentralStudentHub.Model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest  //to not save in actual DB
class UserSessionInfoRepositoryTest {

    @Autowired
    private  UserSessionInfoRepository  userSessionInfoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {

        String email = "ali@gmail.com";
        String password = "1234";

        UserAccount userAccount
                = UserAccount.builder()
                .userType(Role.Student)
                .ssn("12345678901234")
                .email(email)
                .passwordHash(password)
                .passwordSalt("abc")
                .passwordDate(new Date(System.currentTimeMillis()))
                .build();

        //userSessionInfoRepository.save(userAccount);
        entityManager.persist(userAccount);
    }

    @Test
    void existingUserfindByEmail() {
        Optional<UserAccount> userAcc = userSessionInfoRepository.findByEmail("ali@gmail.com");
        assertTrue(userAcc.isPresent());
    }

    @Test
    void nonExistingUserfindByEmail() {
        Optional<UserAccount> userAcc = userSessionInfoRepository.findByEmail("Maro@gmail.com");
        assertFalse(userAcc.isPresent());
    }

    @Test
    void existingUserfindBySsn() {
        Optional<UserAccount> userAcc = userSessionInfoRepository.findBySsn("12345678901234");
        assertTrue(userAcc.isPresent());
    }

    @Test
    void nonExistingUserfindBySsn() {
        Optional<UserAccount> userAcc = userSessionInfoRepository.findBySsn("12345678900000");
        assertFalse(userAcc.isPresent());
    }
}