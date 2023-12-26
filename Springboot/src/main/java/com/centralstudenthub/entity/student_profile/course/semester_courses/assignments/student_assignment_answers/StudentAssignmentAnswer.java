package com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.student_assignment_answers;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_assignment_answer")
public class StudentAssignmentAnswer {

    @EmbeddedId
    private StudentAssignmentAnswerId studentAssignmentAnswerId;

    private String answerPath;
    private Double grade;
}

