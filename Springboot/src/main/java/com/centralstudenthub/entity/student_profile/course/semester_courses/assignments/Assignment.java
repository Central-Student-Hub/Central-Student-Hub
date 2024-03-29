package com.centralstudenthub.entity.student_profile.course.semester_courses.assignments;

import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.student_assignment_answers.StudentAssignmentAnswer;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.assignment_material_paths.AssignmentMaterialPath;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long assignmentId;

    private String assignmentName;
    private String description;
    private LocalDate dueDate;
    private LocalDate submissionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semCourseId", nullable = false)
    private SemesterCourse semCourse;

    @OneToMany(mappedBy = "assignmentMaterialPathId.assignment",fetch = FetchType.EAGER)
    private List<AssignmentMaterialPath> materialPaths;

    @OneToMany(mappedBy = "studentAssignmentAnswerId.assignment",fetch = FetchType.EAGER)
    private List<StudentAssignmentAnswer> answers;

}
