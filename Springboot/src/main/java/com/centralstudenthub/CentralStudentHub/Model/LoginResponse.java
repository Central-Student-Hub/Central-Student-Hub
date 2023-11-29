package com.centralstudenthub.CentralStudentHub.Model;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private boolean accept;
}
