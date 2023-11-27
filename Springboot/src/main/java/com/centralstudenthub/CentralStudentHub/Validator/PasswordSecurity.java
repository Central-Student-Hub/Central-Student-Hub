package com.centralstudenthub.CentralStudentHub.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class PasswordSecurity {
    private static final Random RANDOM = new SecureRandom();

    public String getNextSalt() {
        byte[] bytes = new byte[16];
        RANDOM.nextBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public String hashPassword(String password , String salt){
        String saltedPassword = password + salt;
        return DigestUtils.md5DigestAsHex(saltedPassword.getBytes());
    }

    public boolean verifyPassword(String candidate , String hash , String salt){
        String candidateHash = hashPassword(candidate , salt);
        return candidateHash.equals(hash);
    }
}
