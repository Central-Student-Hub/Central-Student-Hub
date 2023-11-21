package com.centralstudenthub.CentralStudentHub.service;

import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import com.centralstudenthub.CentralStudentHub.repository.UserSessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class SignInService {

    @Autowired
    private UserSessionInfoRepository userSessionInfoRepository;

    public boolean signIn(String email, String password){
        Optional<UserAccount> user = userSessionInfoRepository.findByEmail(email);

        return true;
    }

}
