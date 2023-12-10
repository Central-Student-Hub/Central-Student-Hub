package com.centralstudenthub.Model.Response;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class SignUpResponse {
    private String message;
    private boolean accept;
}
