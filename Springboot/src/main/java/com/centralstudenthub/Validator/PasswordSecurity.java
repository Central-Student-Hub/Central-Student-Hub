package com.centralstudenthub.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class PasswordSecurity {
    private static final Random RANDOM = new SecureRandom();

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getNextSalt() {
        int length = 8;

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char randomChar = generateRandomChar(random);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
    private static char generateRandomChar(Random random) {
        int randomType = random.nextInt(2);
        int asciiOffset = (randomType == 0) ? 65 : 97;
        return (char) (asciiOffset + random.nextInt(26));
    }

    public String hashPassword(String password , String salt){
        String saltedPassword = password + salt;
        return passwordEncoder.encode(saltedPassword);
    }
    public boolean verifyPassword(String candidate , String hash , String salt){
        String candidateHash = hashPassword(candidate , salt);
        return candidateHash.equals(hash);
    }

   /* public static void main(String[] args) {

        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        System.out.println(b.encode("marwan"));
        System.out.println(b.encode("marwan"));
        System.out.println(b.encode("marwan"));
        System.out.println(b.encode("marwan"));

        System.out.println( DigestUtils.md5DigestAsHex("marwan".getBytes()));
        System.out.println( DigestUtils.md5DigestAsHex("marwan".getBytes()));
        System.out.println( DigestUtils.md5DigestAsHex("marwan".getBytes()));
        System.out.println( DigestUtils.md5DigestAsHex("marwan".getBytes()));
    }*/
}
