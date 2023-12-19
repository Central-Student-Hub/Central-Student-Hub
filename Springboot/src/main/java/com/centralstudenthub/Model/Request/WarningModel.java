package com.centralstudenthub.Model.Request;

import java.time.LocalDate;

public record WarningModel(int warningId, String reason, LocalDate date) {
}
