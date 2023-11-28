package com.centralstudenthub.CentralStudentHub.Model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum Role {
    @Enumerated(EnumType.STRING)
    Student,
    @Enumerated(EnumType.STRING)
    Teacher,
    @Enumerated(EnumType.STRING)
    Admin
}
