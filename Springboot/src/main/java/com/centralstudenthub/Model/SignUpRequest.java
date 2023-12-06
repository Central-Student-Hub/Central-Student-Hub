package com.centralstudenthub.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class SignUpRequest {
    @JsonAlias("SSN")
    private String ssn;
    @JsonAlias("email")
    private String email;
    @JsonAlias("password")
    private String password;
    @JsonAlias("type")
    private Role userType;
}
