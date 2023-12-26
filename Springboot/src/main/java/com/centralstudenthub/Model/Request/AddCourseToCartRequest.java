package com.centralstudenthub.Model.Request;

import com.centralstudenthub.Model.Response.sessions.SessionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class AddCourseToCartRequest {
    long courseId;
    int creditHours;
    List<SessionResponse> sessions;
    List<SessionResponse> newSessions;
}
