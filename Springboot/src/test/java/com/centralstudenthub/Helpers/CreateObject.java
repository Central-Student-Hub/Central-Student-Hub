package com.centralstudenthub.Helpers;

import com.centralstudenthub.Model.Semester;
import com.centralstudenthub.Model.SessionType;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.Registration;
import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.RegistrationId;
import com.centralstudenthub.entity.sessions.Session;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGradeId;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateObject {

    public StudentProfile createStudentProfile(int id){
        return StudentProfile.builder()
                .studentId(id).gpa(4.0).grades(null).assignmentAnswers(null).biography(null)
                .contacts(null).firstName(null).lastName(null).level(null).major(null)
                .registrations(null).noOfHours(21).warnings(null).profilePictureUrl(null)
                .build();
    }

    public Course createCourse(int id){
        return   Course.builder().courseId(id).name(null).creditHours(3)
                .grades(null).prerequisites(null).description(null).code(null)
                .build();
    }

    public StudentCourseGradeId createStudentCourseGradeId(Course course,StudentProfile student){

        return StudentCourseGradeId.builder().course(course)
                .student(student).build();
    }
    public StudentCourseGrade createStudentCourseGrade(StudentCourseGradeId studentCourseGradeId){

        return StudentCourseGrade.builder()
                .id(studentCourseGradeId)
                .studentGrade(3.1)
                .build();
    }

    public SemesterCourse createSemesterCourse(long semCourseId,Course course){
        return SemesterCourse.builder()
                .semCourseId(semCourseId).semester(Semester.SUMMER).maxSeats(100)
                .materialPaths(null).announcements(null).assignments(null)
                .registrations(null).feedbacks(null).sessions(null).course(course)
                .build();
    }

    public RegistrationId createregistrationId(StudentProfile studentProfile,SemesterCourse semesterCourse){
        return RegistrationId.builder()
                .student(studentProfile)
                .semCourse(semesterCourse)
                .build();
    }

    public Registration createregistration(RegistrationId registrationId){

        return  Registration.builder().id(registrationId)
                .paymentDeadline(new Date(System.currentTimeMillis() + 100000))
                .paymentFees(100.0)
                .build();
    }

    public Session createSession(long sessId,SemesterCourse semesterCourse,int period){
        return Session.builder()
                .sessionId(sessId).sessionType(SessionType.LECTURE).period(period)
                .semCourse(semesterCourse).teacher(null).weekDay("Saturday").location(null)
                .build();
    }
}
