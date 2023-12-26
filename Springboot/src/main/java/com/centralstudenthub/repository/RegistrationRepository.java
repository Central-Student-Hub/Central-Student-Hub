package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.Registration;
import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.RegistrationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;


@Repository
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {

    @Query("select sum(r.paymentFees) from Registration r where r.id.student.studentId=:studentId")
    double findFeesByStudentID(int studentId);

    @Query("select count(*) from Registration r where r.id.semCourse.semCourseId=:semCourseId")
    int findCountBySemCourse(Long semCourseId);

    @Query(value = "select r.paymentDeadline from registration as r where r.studentId=studentId limit 1"
            ,nativeQuery = true)
    Date findPaymentDeadlineByStudentID(int studentId);
}