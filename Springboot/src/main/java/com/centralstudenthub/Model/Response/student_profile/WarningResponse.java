package com.centralstudenthub.Model.Response.student_profile;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WarningResponse(int warningId, String reason, LocalDate date) {
}
