package com.centralstudenthub.Model.Request;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarningRequest {
    private Integer studentId;
    private String reason;
    private LocalDate date;
}
