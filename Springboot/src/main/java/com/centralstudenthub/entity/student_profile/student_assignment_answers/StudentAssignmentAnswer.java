package com.centralstudenthub.entity.student_profile.student_assignment_answers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
