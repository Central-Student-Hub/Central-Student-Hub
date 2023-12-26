package com.centralstudenthub.Model.Response.student_profile;

import lombok.Builder;

@Builder
public record StudentProfileResponse (
        Integer studentId, String firstName, String lastName, String biography, String profilePictureUrl, String major,
        String minor, Integer level, Integer noOfHours, Double gpa
) {}