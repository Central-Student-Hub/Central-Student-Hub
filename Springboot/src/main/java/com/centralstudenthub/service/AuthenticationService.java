package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.LoginRequest;
import com.centralstudenthub.Model.Request.SignUpRequest;
import com.centralstudenthub.Model.Response.SignUpResponse;
import com.centralstudenthub.Model.Role;
import com.centralstudenthub.Validator.PasswordSecurity;
import com.centralstudenthub.entity.UserAccount;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.repository.StudentProfileRepository;
import com.centralstudenthub.repository.TeachingStaffProfileRepository;
import com.centralstudenthub.repository.UserSessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserSessionInfoRepository userSessionInfoRepository;

    @Autowired
    private PasswordSecurity passwordSecurity;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private TeachingStaffProfileRepository teachingStaffProfileRepository;

    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        Optional<UserAccount> dbUser = userSessionInfoRepository.findBySsn(signUpRequest.getSsn());

        if (dbUser.isEmpty()) return new SignUpResponse("You don't have access", false);
        if (dbUser.get().getEmail() != null)
            return new SignUpResponse("You already have an account with this email", false);

        Optional<UserAccount> checkEmail = userSessionInfoRepository.findByEmail(signUpRequest.getEmail());
        if (checkEmail.isPresent()) return new SignUpResponse("Email Already Exists", false);

        UserAccount user = dbUser.get();
        String salt = passwordSecurity.getNextSalt();
        String hashedPassword = passwordSecurity.hashPassword(signUpRequest.getPassword(), salt);
        user.setUserType(signUpRequest.getUserType());
        user.setEmail(signUpRequest.getEmail());
        user.setPasswordHash(hashedPassword);
        user.setPasswordSalt(salt);
        user.setPasswordDate(new Date(System.currentTimeMillis()));

        long userID = userSessionInfoRepository.save(user).getUserAccountId();
        if (user.getUserType().equals(Role.Student)) {
            studentProfileRepository.save(StudentProfile.builder()
                    .studentId((int) userID)
                    .firstName("First Name")
                    .lastName("Last Name")
                    .gpa(0.0)
                    .level(0)
                    .major("None")
                    .minor("None")
                    .noOfHours(18)
                    .build()
            );
        } else if (user.getUserType().equals(Role.Staff)) {
            teachingStaffProfileRepository.save(TeachingStaffProfile.builder().
                    teacherId((int) userID)
                    .firstName("First Name")
                    .lastName("Last Name")
                    .department("Null Department")
                    .biography("Null Biography")
                    .build());
        }

        return new SignUpResponse("Account Created Successfully", true);
    }


    public String login(LoginRequest loginRequest) {

        Optional<UserAccount> user = userSessionInfoRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) return null;

        String salt = user.get().getPasswordSalt();
        String pass = loginRequest.getPassword() + salt;

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        pass
                )
        );
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", user.get().getUserAccountId());
        extraClaims.put("role", user.get().getUserType());
        String token = jwtService.generateToken(extraClaims, user.get());

        return token;
    }

//    public String processOAuthPostLogin(OAuth2AuthenticationToken authentication) {
//
//        String gmail = ((OAuth2User)authentication.getPrincipal()).getAttribute("email");
//        Optional<UserAccount> existUser = userSessionInfoRepository.findByGmail(gmail);
//        if (existUser.isEmpty()) return null;
//
//        return jwtService.generateToken(existUser.get());
//    }

    public boolean addUser(String ssn) {
        Optional<UserAccount> existingUser = userSessionInfoRepository.findBySsn(ssn);

        if (existingUser.isPresent())
            return false;

        UserAccount user = UserAccount.builder()
                .ssn(ssn)
                .build();

        userSessionInfoRepository.save(user);
        return true;
    }
}
