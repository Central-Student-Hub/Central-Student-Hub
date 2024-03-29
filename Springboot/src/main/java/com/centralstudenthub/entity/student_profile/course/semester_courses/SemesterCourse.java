package com.centralstudenthub.entity.student_profile.course.semester_courses;

import com.centralstudenthub.Model.Response.student_profile.course.semester_courses.SemesterCourseResponse;
import com.centralstudenthub.Model.Semester;
import com.centralstudenthub.entity.exam.Exam;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.Assignment;
import com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths.CourseMaterialPath;
import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.Registration;
import com.centralstudenthub.entity.sessions.Session;
import com.centralstudenthub.entity.student_profile.course.Course;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "semester_course")
public class SemesterCourse {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long semCourseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    private Integer maxSeats;

    @OneToMany(mappedBy = "id.semCourse",fetch = FetchType.EAGER)
    private List<CourseMaterialPath> materialPaths;

    @OneToMany(mappedBy = "semCourse",fetch = FetchType.EAGER)
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "semCourse",fetch = FetchType.EAGER)
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "semCourse")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "semCourse", fetch = FetchType.EAGER)
    private List<Session> sessions;

    @OneToMany(mappedBy = "id.semCourse", fetch = FetchType.EAGER)
    private List<Registration> registrations;

    @OneToMany(mappedBy = "semesterCourse")
    private List<Exam> exams;

    public SemesterCourseResponse toResponse() {
        return SemesterCourseResponse.builder()
                .code(course.getCode())
                .name(course.getName())
                .description(course.getDescription())
                .creditHours(course.getCreditHours())
                .prerequisitesCodes(course.getPrerequisites().stream().map(prereq -> prereq.getId().getCourse().getCode()).toList())
                .sessions(sessions.stream().map(Session::toResponse).toList())
                .semCourseId(semCourseId)
                .semester(semester)
                .maxSeats(maxSeats)
                .build();
    }
}
