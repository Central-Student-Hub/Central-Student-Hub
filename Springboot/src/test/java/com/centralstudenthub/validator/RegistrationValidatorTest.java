package com.centralstudenthub.validator;

import com.centralstudenthub.Validator.RegistrationValidator;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisite;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisiteId;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.Session;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGradeId;
import com.centralstudenthub.exception.NullCourseException;
import com.centralstudenthub.exception.NullRegisteredSessionsException;
import com.centralstudenthub.exception.NullSemesterCourseException;
import com.centralstudenthub.exception.NullStudentProfileException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RegistrationValidatorTest {

    @Autowired
    RegistrationValidator registrationValidator;

 //   @Nested
//    class SeatsTests {
//        @Test
//        void testSeatsAvailable() throws NullSemesterCourseException {
//            int maxSeats = 5;
//
//            List<CourseMember> members = new ArrayList<>();
//            for (int i = 0; i < maxSeats - 1; i++)
//                members.add(new CourseMember());
//
//            SemesterCourse registeringCourse = new SemesterCourse();
//            registeringCourse.setMaxSeats(maxSeats);
//            registeringCourse.setMembers(members);
//
//            assertEquals("Seats Available", registrationValidator.validateSeatsAvailable(registeringCourse));
//        }
//
//        @Test
//        void testSeatsUnavailable() throws NullSemesterCourseException {
//            List<CourseMember> members = new ArrayList<>();
//            SemesterCourse semesterCourse = new SemesterCourse();
//
//            int maxSeats = 5;
//            for (int i = 0; i < maxSeats; i++)
//                members.add(new CourseMember());
//
//            semesterCourse.setMaxSeats(maxSeats);
//            semesterCourse.setMembers(members);
//
//            assertEquals("Seats Unavailable", registrationValidator.validateSeatsAvailable(semesterCourse));
//        }
//
//        @Test
//        void testNullSeatsSemesterCourse() {
//            SemesterCourse semesterCourse = null;
//            assertThrows(NullSemesterCourseException.class, () -> {
//                registrationValidator.validateSeatsAvailable(semesterCourse);
//            });
//        }
//    }
//
//    @Nested
//    class PrerequisitesTests {
//        static Course registeringCourse = Course.builder().courseId(6).build();
//
//        static Course c1 = Course.builder().courseId(1).build();
//        static Course c2 = Course.builder().courseId(2).build();
//        static Course c3 = Course.builder().courseId(3).build();
//        static Course c4 = Course.builder().courseId(4).build();
//        static Course c5 = Course.builder().courseId(5).build();
//
//        static StudentProfile student = new StudentProfile();
//
//        @BeforeAll
//        static void addCourses() {
//            List<CoursePrerequisite> coursePrerequisites = new ArrayList<>();
//            coursePrerequisites.add(
//                    CoursePrerequisite.builder().id(
//                            CoursePrerequisiteId.builder()
//                                    .course(registeringCourse)
//                                    .prerequisite(c1)
//                                    .build()
//                    )
//                    .build()
//            );
//
//            coursePrerequisites.add(
//                    CoursePrerequisite.builder().id(
//                            CoursePrerequisiteId.builder()
//                                    .course(registeringCourse)
//                                    .prerequisite(c2)
//                                    .build()
//                    )
//                    .build()
//            );
//
//            coursePrerequisites.add(
//                    CoursePrerequisite.builder().id(
//                            CoursePrerequisiteId.builder()
//                                    .course(registeringCourse)
//                                    .prerequisite(c3)
//                                    .build()
//                    )
//                    .build()
//            );
//
//            registeringCourse.setPrerequisites(coursePrerequisites);
//        }
//
//        @BeforeAll
//        static void addStudentGrades() {
//
//            StudentCourseGrade g1 = StudentCourseGrade.builder()
//                    .id(StudentCourseGradeId.builder()
//                            .student(student)
//                            .course(c1)
//                            .build()
//                    )
//                    .studentGrade(70.0)
//                    .build();
//
//            StudentCourseGrade g2 = StudentCourseGrade.builder()
//                    .id(StudentCourseGradeId.builder()
//                            .student(student)
//                            .course(c2)
//                            .build()
//                    )
//                    .studentGrade(60.0)
//                    .build();
//
//            StudentCourseGrade g3 = StudentCourseGrade.builder()
//                    .id(StudentCourseGradeId.builder()
//                            .student(student)
//                            .course(c3)
//                            .build()
//                    )
//                    .studentGrade(95.0)
//                    .build();
//
//            StudentCourseGrade g4 = StudentCourseGrade.builder()
//                    .id(StudentCourseGradeId.builder()
//                            .student(student)
//                            .course(c4)
//                            .build()
//                    )
//                    .studentGrade(90.0)
//                    .build();
//
//            StudentCourseGrade g5 = StudentCourseGrade.builder()
//                    .id(StudentCourseGradeId.builder()
//                            .student(student)
//                            .course(c5)
//                            .build()
//                    )
//                    .studentGrade(43.0)
//                    .build();
//
//            List<StudentCourseGrade> grades = new ArrayList<>();
//            grades.add(g1);
//            grades.add(g2);
//            grades.add(g3);
//            grades.add(g4);
//            grades.add(g5);
//
//            student.setGrades(grades);
//        }
//
//        @Test
//        void testSatisfiedPrerequisitesAllPassed() throws NullStudentProfileException, NullCourseException {
//            assertEquals("Prerequisites Satisfied", registrationValidator.validatePrerequisites(student, registeringCourse));
//        }
//
//        @Test
//        void testUnsatisfiedPrerequisites() throws NullStudentProfileException, NullCourseException {
//            StudentCourseGrade g1 = student.getGrades().get(0);
//            student.getGrades().remove(0);
//            assertEquals("Prerequisites Unsatisfied", registrationValidator.validatePrerequisites(student, registeringCourse));
//            student.getGrades().add(0, g1);
//        }
//
//        @Test
//        void testUnsatisfiedPrerequisitesFailedCourse() throws NullStudentProfileException, NullCourseException {
//            double grade1 = student.getGrades().get(0).getStudentGrade();
//            student.getGrades().get(0).setStudentGrade(45.0);
//            assertEquals("One or More Courses Failed", registrationValidator.validatePrerequisites(student, registeringCourse));
//            student.getGrades().get(0).setStudentGrade(grade1);
//        }
//
//        @Test
//        void testNullStudentProfile() {
//            StudentProfile nullStudent = null;
//            assertThrows(NullStudentProfileException.class, () -> {
//                registrationValidator.validatePrerequisites(nullStudent, registeringCourse);
//            });
//        }
//
//        @Test
//        void testNullCourse() {
//            Course nullCourse = null;
//            assertThrows(NullCourseException.class, () -> {
//                registrationValidator.validatePrerequisites(student, nullCourse);
//            });
//        }
//    }
//
//    @Nested
//    class CreditHoursLimitTests {
//        @Test
//        void testSatisfiedCreditHoursLimit() throws NullStudentProfileException, NullCourseException {
//            int studentCurrentCreditHours = 14;
//
//            Course registeringCourse = Course.builder().courseId(1).creditHours(3).build();
//            StudentProfile student = StudentProfile.builder().studentId(1).gpa(3.0).build();
//
//            assertEquals("Credit Hours Limit Satisfied", registrationValidator.validateCreditHoursLimit(student, registeringCourse, studentCurrentCreditHours));
//        }
//
//        @Test
//        void testUnsatisfiedCreditHoursLimit() throws NullStudentProfileException, NullCourseException {
//            int studentCurrentCreditHours = 16;
//
//            Course registeringCourse = Course.builder().courseId(1).creditHours(3).build();
//            StudentProfile student = StudentProfile.builder().studentId(1).gpa(2.7).build();
//
//            assertEquals("Credit Hours Limit Unsatisfied", registrationValidator.validateCreditHoursLimit(student, registeringCourse, studentCurrentCreditHours));
//        }
//
//        @Test
//        void testNullStudentProfile() {
//            int studentCurrentCreditHours = 14;
//
//            Course registeringCourse = Course.builder().courseId(1).creditHours(3).build();
//            StudentProfile student = null;
//
//            assertThrows(NullStudentProfileException.class, () -> {
//                registrationValidator.validateCreditHoursLimit(student, registeringCourse, studentCurrentCreditHours);
//            });
//        }
//
//        @Test
//        void testNullRegisteringCourse() {
//            int studentCurrentCreditHours = 14;
//
//            Course registeringCourse = null;
//            StudentProfile student = StudentProfile.builder().studentId(1).gpa(2.7).build();
//
//            assertThrows(NullCourseException.class, () -> {
//                registrationValidator.validateCreditHoursLimit(student, registeringCourse, studentCurrentCreditHours);
//            });
//        }
//    }
//
//    @Nested
//    class SessionTimesTests {
//        static List<Session> registeredSessions = new ArrayList<>();
//
//        @BeforeAll
//        static void addSessions() {
//            Session s1 = Session.builder().period(1).build();
//            Session s2 = Session.builder().period(2).build();
//            Session s3 = Session.builder().period(3).build();
//            Session s4 = Session.builder().period(4).build();
//
//            registeredSessions.add(s1);
//            registeredSessions.add(s2);
//            registeredSessions.add(s3);
//            registeredSessions.add(s4);
//        }
//
//        @Test
//        void testNonConflictingSessionTimes() throws NullRegisteredSessionsException, NullSemesterCourseException {
//            Session s5 = Session.builder().period(5).build();
//            assertEquals("No Conflicting Session Times", registrationValidator.validateSessionTimes(registeredSessions, s5));
//        }
//
//        @Test
//        void testConflictingSessionTimes() throws NullRegisteredSessionsException, NullSemesterCourseException {
//            Session s5 = Session.builder().period(1).build();
//            assertEquals("Conflicting Session Times", registrationValidator.validateSessionTimes(registeredSessions, s5));
//        }
//
//        @Test
//        void testNullRegisteredCourses() {
//            List<Session> nullRegisteredSessions = null;
//            Session s5 = new Session();
//            assertThrows(NullRegisteredSessionsException.class, () -> {
//                registrationValidator.validateSessionTimes(nullRegisteredSessions, s5);
//            });
//        }
//
//        @Test
//        void testNullNewSemesterCourse() {
//            Session s5 = null;
//            assertThrows(NullSemesterCourseException.class, () -> {
//                registrationValidator.validateSessionTimes(registeredSessions, s5);
//            });
//        }
//    }
}
