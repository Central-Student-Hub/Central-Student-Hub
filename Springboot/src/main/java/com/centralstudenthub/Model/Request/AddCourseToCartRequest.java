package com.centralstudenthub.Model.Request;

import com.centralstudenthub.Model.Response.SessionResponse;
import com.centralstudenthub.Model.SessionModel;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class AddCourseToCartRequest {
    long courseId;
    int creditHours;
    List<SessionModel> sessions;
    List<SessionModel> newSessions;
}
