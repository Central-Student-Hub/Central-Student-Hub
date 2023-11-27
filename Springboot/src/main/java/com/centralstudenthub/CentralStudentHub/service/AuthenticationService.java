package com.centralstudenthub.CentralStudentHub.service;

import com.centralstudenthub.CentralStudentHub.entity.Role;
import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import com.centralstudenthub.CentralStudentHub.repository.UserSessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserSessionInfoRepository userSessionInfoRepository;

    public boolean signUp(String email, String password){
        UserAccount user = UserAccount.builder()
                .ssn("xx-xy-yy")
                .userType(Role.Student)
                .email(email)
                .passwordHash("ams")
                .passwordSalt("aa")
                .passwordDate(null)
                .build();

        userSessionInfoRepository.save(user);

        return true;
    }

    public boolean login(String email, String password){
        Optional<UserAccount> user = userSessionInfoRepository.findByEmail(email);

        return true;
    }

}
