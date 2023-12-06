package com.centralstudenthub.Model;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class SignUpResponse {
    private String message;
    private boolean accept;
}
