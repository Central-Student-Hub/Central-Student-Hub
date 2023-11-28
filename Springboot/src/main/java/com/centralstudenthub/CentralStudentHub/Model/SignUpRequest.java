package com.centralstudenthub.CentralStudentHub.Model;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class SignUpRequest {
    private String ssn;
    private String email;
    private String password;
    private String userType;
}
