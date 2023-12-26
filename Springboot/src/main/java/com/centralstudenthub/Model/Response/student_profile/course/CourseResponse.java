package com.centralstudenthub.Model.Response.student_profile.course;

import lombok.Builder;

@Builder
public record CourseResponse (Integer courseId, String code, String name, String description, Integer creditHours) {}
