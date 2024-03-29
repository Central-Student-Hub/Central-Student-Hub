package com.centralstudenthub.Validator;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisite;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.exception.NullCourseException;
import com.centralstudenthub.exception.NullRegisteredSessionsException;
import com.centralstudenthub.exception.NullSemesterCourseException;
import com.centralstudenthub.exception.NullStudentProfileException;
import com.centralstudenthub.entity.sessions.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationValidator {

    public boolean validateSeatsAvailable(SemesterCourse semesterCourse) throws NullSemesterCourseException {
        if (semesterCourse == null) {
            throw new NullSemesterCourseException();
        }

        int takenSeats = semesterCourse.getRegistrations().size();
        int maxSeats = semesterCourse.getMaxSeats();

        if (takenSeats != maxSeats) {
            return true;
        } else {
            return false;
        }
    }

    public String validatePrerequisites(StudentProfile student, Course course) throws NullStudentProfileException, NullCourseException {
        if (student == null) {
            throw new NullStudentProfileException();
        }

        if (course == null) {
            throw new NullCourseException();
        }

        if(course.getPrerequisites() == null)return "Prerequisites Satisfied";

        List<StudentCourseGrade> grades = student.getGrades();
        for (CoursePrerequisite prerequisite : course.getPrerequisites()) {
            List<StudentCourseGrade> filteredGrades = grades.stream().filter((grade) ->
                    grade.getId().getCourse() == prerequisite.getId().getPrerequisite()
            ).toList();

            if (filteredGrades.isEmpty()) {
                return "Prerequisites Unsatisfied";
            } else {
                List<StudentCourseGrade> failedCourses = filteredGrades.stream().filter(grade ->
                        grade.getStudentGrade() < 60.0
                ).toList();

                if (!failedCourses.isEmpty()) {
                    return "One or More Courses Failed";
                }
            }
        }

        return "Prerequisites Satisfied";
    }

    public boolean validateCreditHoursLimit(StudentProfile student, Course course, int currentCreditHours)
            throws NullStudentProfileException, NullCourseException {
        if (student == null) {
            throw new NullStudentProfileException();
        }

        if (course == null) {
            throw new NullCourseException();
        }

        int maxCreditHours;
        if (student.getGpa() > 3.0) {
            maxCreditHours = 21;
        } else if (student.getGpa() > 2.0) {
            maxCreditHours = 18;
        } else {
            maxCreditHours = 14;
        }

        if (course.getCreditHours() + currentCreditHours <= maxCreditHours) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateSessionTimes(List<Session> registeredSessions, List<Session> newSessions)
            throws NullRegisteredSessionsException, NullSemesterCourseException {
        if (registeredSessions == null) {
            throw new NullRegisteredSessionsException();
        }

        for (Session newSession : newSessions) {
            if (newSession == null) {
                throw new NullSemesterCourseException();
            }

            List<Session> conflictingSessions = registeredSessions.stream().filter(session ->
                    session.getPeriod().equals(newSession.getPeriod())
            ).toList();

            if (!conflictingSessions.isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
