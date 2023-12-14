package com.centralstudenthub.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginRequest {
    private String email;
    private String password;
}
