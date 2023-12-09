package com.centralstudenthub.Model;

import com.centralstudenthub.entity.StudentProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class WarningRequest {
    private Integer warningId;
    private String reason;
    private LocalDate date;
}
