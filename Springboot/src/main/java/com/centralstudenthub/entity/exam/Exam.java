package com.centralstudenthub.entity.exam;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location.Location;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "exam")
public class Exam {

    @EmbeddedId
    private ExamId id;

    @ManyToOne
    @MapsId("semCourseId")
    @JoinColumn(name = "semCourseId", nullable = false)
    private SemesterCourse semesterCourse;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;

    @Column(nullable = false)
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "building", nullable = false)
    private Location building;

    @ManyToOne
    @JoinColumn(name = "room", nullable = false)
    private Location room;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int period;
}

