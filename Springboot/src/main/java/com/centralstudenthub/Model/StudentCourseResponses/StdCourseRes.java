package com.centralstudenthub.Model.StudentCourseResponses;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StdCourseRes {
    Long semCourseId;
    String semCourseCode;
    String semCourseName;
    String semCourseDescription;
    int semCourseCreditHours;
    String teacherFirstName;
    String teacherLastName;
    int teacherId;
}
