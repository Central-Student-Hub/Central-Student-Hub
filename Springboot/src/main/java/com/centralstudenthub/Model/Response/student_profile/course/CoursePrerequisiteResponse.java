package com.centralstudenthub.Model.Response.student_profile.course;

import lombok.Builder;

@Builder
public record CoursePrerequisiteResponse (Integer courseId, Integer prerequisiteId) {}