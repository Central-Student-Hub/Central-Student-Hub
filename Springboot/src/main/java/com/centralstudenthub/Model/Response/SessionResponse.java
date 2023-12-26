package com.centralstudenthub.Model.Response;

import com.centralstudenthub.Model.SessionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponse {
    private Long sessionId;
    private Integer period;
    private String weekDay;
    private SessionType sessionType;
}
