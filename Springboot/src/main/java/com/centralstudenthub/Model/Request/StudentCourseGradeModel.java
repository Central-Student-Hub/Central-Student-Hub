package com.centralstudenthub.Model.Request;

import lombok.Builder;

@Builder
public record StudentCourseGradeModel(String courseName, String courseCode, int noOfHours, double grade) {
}
