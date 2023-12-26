package com.centralstudenthub.entity.exam;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location.LocationId;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

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
    @JoinColumn(name = "semCourseId", referencedColumnName = "semCourseId")
    private SemesterCourse semesterCourse;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private StudentProfile student;

    @Column(nullable = false)
    private int seatNumber;

    private LocationId locationId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private float fromTime;

    @Column(nullable = false)
    private float period;
}

