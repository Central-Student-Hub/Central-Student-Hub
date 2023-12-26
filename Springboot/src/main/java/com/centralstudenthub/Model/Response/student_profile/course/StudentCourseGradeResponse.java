package com.centralstudenthub.Model.Response.student_profile.course;

import lombok.Builder;

@Builder
public record StudentCourseGradeResponse(String courseName, String courseCode, int noOfHours, double grade) {
}
