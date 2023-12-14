package com.centralstudenthub.Model.Request;

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
    int studentId;
    long courseId;
    int creditHours;
    List<Session> sessions;
    Session newSession;
}
