package com.centralstudenthub.Model.Response;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private boolean accept;
}
