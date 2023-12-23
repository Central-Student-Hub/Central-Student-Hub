package com.centralstudenthub.Model.Request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WarningModel(int warningId, String reason, LocalDate date) {
}
