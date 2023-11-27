package com.centralstudenthub.CentralStudentHub.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SignUpResponse {
    private String message;
    private boolean accept;
}
