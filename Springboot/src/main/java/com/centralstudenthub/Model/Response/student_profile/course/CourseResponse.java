package com.centralstudenthub.Model.Response.student_profile.course;

import com.centralstudenthub.entity.student_profile.course.Course;
import lombok.Builder;

@Builder
public record CourseResponse (Integer courseId, String code, String name, String description, Integer creditHours) {
    public Course toEntity() {
        return Course.builder()
                .courseId(courseId)
                .code(code)
                .name(name)
                .description(description)
                .creditHours(creditHours)
                .build();
    }
}
