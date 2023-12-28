package com.centralstudenthub.entity.student_profile.course;

import com.centralstudenthub.Model.Response.student_profile.course.CourseResponse;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisite;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course")
@Getter
@Setter
public class Course {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId;

    @Column(nullable = false, unique = true)
    private String code;

    private String name;
    private String description;
    private Integer creditHours;

    @OneToMany(mappedBy = "id.course", fetch = FetchType.EAGER)
    private List<CoursePrerequisite> prerequisites;

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private List<SemesterCourse> semesterCourses;

    @OneToMany(mappedBy = "id.course")
    private List<StudentCourseGrade> grades;

    public CourseResponse toResponse() {
        return CourseResponse.builder()
                .courseId(courseId)
                .code(code)
                .name(name)
                .description(description)
                .creditHours(creditHours)
                .build();
    }
}
