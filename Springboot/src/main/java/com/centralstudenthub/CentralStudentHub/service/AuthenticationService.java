package com.centralstudenthub.CentralStudentHub.service;

import com.centralstudenthub.CentralStudentHub.Model.SignUpRequest;
import com.centralstudenthub.CentralStudentHub.Model.SignUpResponse;
import com.centralstudenthub.CentralStudentHub.Validator.PasswordSecurity;
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

    @Autowired
    private PasswordSecurity passwordSecurity;

    public SignUpResponse signUp(SignUpRequest signUpRequest){

        Optional<UserAccount> DBuser = userSessionInfoRepository.findBySsn(signUpRequest.getSsn());
        if(DBuser.isPresent() && DBuser.get().getEmail() == null){

            Optional<UserAccount> checkEmail = userSessionInfoRepository.findByEmail(signUpRequest.getEmail());
            if(checkEmail.isEmpty()){
                UserAccount user = DBuser.get();
                String salt = passwordSecurity.getNextSalt();
                String hashedPassword = passwordSecurity
                        .hashPassword(signUpRequest.getPassword(),salt);

                user.setUserType(signUpRequest.getUserType());
                user.setEmail(signUpRequest.getEmail());
                user.setPasswordHash(hashedPassword);
                user.setPasswordSalt(salt);
                user.setPasswordDate(new Date(System.currentTimeMillis()));

                userSessionInfoRepository.save(user);
                return new SignUpResponse("Account Created Successfully",true);
            }
            else{
                return new SignUpResponse("Email Already Exists",false);
            }
        }
        return new SignUpResponse("You don't have access",false);
    }


    public String login(String email, String password){



        return "hi";
    }

}