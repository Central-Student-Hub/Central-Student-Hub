package com.centralstudenthub.CentralStudentHub.service;

import com.centralstudenthub.CentralStudentHub.Model.Role;
import com.centralstudenthub.CentralStudentHub.Model.SignUpRequest;
import com.centralstudenthub.CentralStudentHub.Model.SignUpResponse;
import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import com.centralstudenthub.CentralStudentHub.repository.UserSessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserSessionInfoRepository userSessionInfoRepository;

    public SignUpResponse signUp(SignUpRequest signUpRequest){

        Optional<UserAccount> DBuser = userSessionInfoRepository.findBySsn(signUpRequest.getSsn());
        if(DBuser.isPresent() && DBuser.get().getEmail().isEmpty()){

            Optional<UserAccount> checkEmail = userSessionInfoRepository.findByEmail(signUpRequest.getEmail());
            if(checkEmail.isEmpty()){
                UserAccount user = UserAccount.builder()
                        .ssn(signUpRequest.getSsn())
                        .userType(signUpRequest.getUserType())
                        .email(signUpRequest.getEmail())
                        .passwordHash(signUpRequest.getPassword())
                        .passwordSalt(signUpRequest.getPassword())//todo: add password salt
                        .passwordDate(GetDate())
                        .build();

                userSessionInfoRepository.save(user);
                return new SignUpResponse("Account Created Successfully",true);
            }
            else{
                return new SignUpResponse("Email Already Exists",false);
            }
        }
        return new SignUpResponse("El3ab B3ed yala",false);
    }


    public String login(String email, String password){
        Optional<UserAccount> user = userSessionInfoRepository.findByEmail(email);

        return "hi";
    }

    public Date GetDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy/ HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date date = Date.valueOf(dtf.format(now));
        return date;
    }


}
