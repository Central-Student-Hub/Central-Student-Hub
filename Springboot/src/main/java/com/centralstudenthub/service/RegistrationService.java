package com.centralstudenthub.service;

import com.centralstudenthub.Validator.RegistrationValidator;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationValidator registrationValidator;
    public boolean addCourseToCart(int studentId, int courseId) {

        return false;
    }

    public List<SemesterCourse> getAvailableCourses(int studentId) {

        return null;
    }

    public boolean checkOut(int studentId, String courseIds) {
        return false;
    }

    public boolean unregisterCourse(int studentId, int courseId) {
        return false;
    }

    public List<SemesterCourse> searchForCourse(String searchPhrase) {
        return null;
    }

    public float showFees(int studentId) {
        return 0;
    }

    public Date getPaymentDeadLine() {
        return null;
    }
}
