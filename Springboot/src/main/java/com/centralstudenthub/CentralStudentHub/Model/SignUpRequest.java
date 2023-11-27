package com.centralstudenthub.CentralStudentHub.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    private String ssn;
    private String email;
    private String password;
    private String userType;
}
