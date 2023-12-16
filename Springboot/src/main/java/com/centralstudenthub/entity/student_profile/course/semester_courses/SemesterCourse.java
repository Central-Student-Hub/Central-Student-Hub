package com.centralstudenthub.entity.student_profile.course.semester_courses;

import com.centralstudenthub.Model.Response.SemesterCourseResponse;
import com.centralstudenthub.Model.Semester;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.Assignment;
import com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths.CourseMaterialPath;
import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.Registration;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.Session;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    private Integer maxSeats;

    @OneToMany(mappedBy = "id.semCourse")
    private List<CourseMaterialPath> materialPaths;

    @OneToMany(mappedBy = "semCourse")
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "semCourse")
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "semCourse")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "semCourse")
    private List<Session> sessions;

    @OneToMany(mappedBy = "id.semCourse")
    private List<Registration> registrations;

    public SemesterCourseResponse toResponse() {
        return SemesterCourseResponse.builder()
                .semCourseId(semCourseId)
                .code(course.getCode())
                .name(course.getName())
                .description(course.getDescription())
                .creditHours(course.getCreditHours())
                .semester(semester)
                .maxSeats(maxSeats)
                .build();
    }
}
