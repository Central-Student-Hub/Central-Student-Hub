//package com.centralstudenthub.service;
//
//import com.centralstudenthub.Helpers.CreateObject;
//import com.centralstudenthub.Model.Request.AddCourseToCartRequest;
//import com.centralstudenthub.Model.Semester;
//import com.centralstudenthub.Model.SessionType;
//import com.centralstudenthub.Validator.RegistrationValidator;
//import com.centralstudenthub.entity.student_profile.StudentProfile;
//import com.centralstudenthub.entity.student_profile.course.Course;
//import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisite;
//import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisiteId;
//import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
//import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.Registration;
//import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.RegistrationId;
//import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.Session;
//import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
//import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGradeId;
//import com.centralstudenthub.exception.NullCourseException;
//import com.centralstudenthub.exception.NullRegisteredSessionsException;
//import com.centralstudenthub.exception.NullSemesterCourseException;
//import com.centralstudenthub.exception.NullStudentProfileException;
//import com.centralstudenthub.repository.CourseRepository;
//import com.centralstudenthub.repository.RegistrationRepository;
//import com.centralstudenthub.repository.SemesterCourseRepository;
//import com.centralstudenthub.repository.StudentProfileRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class RegistrationServiceTest {
//
//    @MockBean
//    private RegistrationRepository registrationRepository;
//    @MockBean
//    private StudentProfileRepository studentProfileRepository;
//    @MockBean
//    private SemesterCourseRepository semesterCourseRepository;
//    @Autowired
//    private RegistrationValidator registrationValidator;
//    @Autowired
//    private RegistrationService registrationService;
//
//    @MockBean
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private SearchService searchService;
//
//    @Autowired
//    private CreateObject createObject;
//
//    StudentProfile studentProfile1;
//    SemesterCourse semesterCourse1;
//    SemesterCourse semesterCourse2;
//
//    @BeforeEach
//    void setUp() {
//
//        //Create intiall Objects for student 1
//        studentProfile1 =  createObject.createStudentProfile(1);
//        Course               course1 = createObject.createCourse(1);
//        StudentCourseGradeId studentCourseGradeId1 = createObject.createStudentCourseGradeId(course1,studentProfile1);
//        StudentCourseGrade   studentCourseGrade1 = createObject.createStudentCourseGrade(studentCourseGradeId1);
//        semesterCourse1 = createObject.createSemesterCourse(1l,course1);
//        RegistrationId       registrationId1 = createObject.createregistrationId(studentProfile1,semesterCourse1);
//        Registration         registration1 = createObject.createregistration(registrationId1);
//        Session              session1 = createObject.createSession(1l,semesterCourse1,1);
//
//        Course               course2 = createObject.createCourse(2);
//        StudentCourseGradeId studentCourseGradeId2 = createObject.createStudentCourseGradeId(course2,studentProfile1);
//        StudentCourseGrade   studentCourseGrade2 = createObject.createStudentCourseGrade(studentCourseGradeId2);
//        semesterCourse2 = createObject.createSemesterCourse(2l,course2);
//        RegistrationId       registrationId2 = createObject.createregistrationId(studentProfile1,semesterCourse2);
//        Registration         registration2 = createObject.createregistration(registrationId2);
//        Session              session2 = createObject.createSession(2l,semesterCourse2,2);
//
//        List<Registration> course1Registrations = Arrays.asList(registration1);
//        List<Registration> course2Registrations = Arrays.asList(registration2);
//        List<Registration> student1Registration = Arrays.asList(registration1,registration2);
//        //update Object
//        semesterCourse1.setRegistrations(course1Registrations);
//        semesterCourse1.setSessions(Collections.singletonList(session1));
//        course1.setName("Math1");
//        course1.setCode("Math1");
//        course1.setDescription("Math1");
//
//        semesterCourse2.setRegistrations(course2Registrations);
//        semesterCourse2.setSessions(Arrays.asList(session2));
//        course2.setName("Math2");
//        course2.setCode("Math2");
//        course2.setDescription("Math2");
//
//        studentProfile1.setRegistrations(student1Registration);
//        course1.setSemesterCourses(Arrays.asList(semesterCourse1));
//        course2.setSemesterCourses(Arrays.asList(semesterCourse2));
//
//        course1.setGrades(Arrays.asList(studentCourseGrade1));
//        course2.setGrades(Arrays.asList(studentCourseGrade2));
//
//        studentProfile1.setGrades(Arrays.asList(studentCourseGrade1,studentCourseGrade2));
//    }
//
//    @Test
//    void addCourseToCart() throws NullStudentProfileException, NullRegisteredSessionsException, NullCourseException, NullSemesterCourseException {
//        List<Session> sessions = new ArrayList<>();
//        for(StudentCourseGrade stdgrade:studentProfile1.getGrades()){
//            for(SemesterCourse semCourse : stdgrade.getId().getCourse().getSemesterCourses()){
//                sessions.add(semCourse.getSessions().get(0));
//            }
//        }
//
//        Course               course3 = createObject.createCourse(3);
//        StudentCourseGradeId studentCourseGradeId3 = createObject.createStudentCourseGradeId(course3,studentProfile1);
//        StudentCourseGrade   studentCourseGrade3 = createObject.createStudentCourseGrade(studentCourseGradeId3);
//        SemesterCourse       semesterCourse3 = createObject.createSemesterCourse(3l,course3);
//        RegistrationId       registrationId3 = createObject.createregistrationId(studentProfile1,semesterCourse3);
//        Registration         registration3 = createObject.createregistration(registrationId3);
//        Session              newsession = createObject.createSession(3l,semesterCourse3,3);
//
//        semesterCourse3.setSessions(Arrays.asList(newsession));
//        semesterCourse3.setRegistrations(Arrays.asList(registration3));
//        course3.setSemesterCourses(Arrays.asList(semesterCourse3));
//
//
//        AddCourseToCartRequest addCourseToCartRequest = AddCourseToCartRequest.builder()
//                .courseId(3l)
//                .creditHours(3)
//                .sessions(sessions)
//                .newSession(newsession)
//                .build();
//
//        Mockito.when(semesterCourseRepository.findById(3l))
//                .thenReturn(Optional.ofNullable(semesterCourse3));
//
//        Mockito.when(studentProfileRepository.findById(1))
//                .thenReturn(Optional.ofNullable(studentProfile1));
//
//        boolean res = registrationService.addCourseToCart(addCourseToCartRequest);
//        assertEquals(true,res);
//
//
//    }
//
//    @Test
//    void getAvailableCourses() {
//
//        Course               course3 = createObject.createCourse(3);
//        SemesterCourse       semesterCourse3 = createObject.createSemesterCourse(3l,course3);
//        Session              newsession3 = createObject.createSession(3l,semesterCourse3,3);
//        semesterCourse3.setSessions(Arrays.asList(newsession3));
//        course3.setSemesterCourses(Arrays.asList(semesterCourse3));
//        course3.setName("Math3");
//        course3.setCode("Math3");
//        course3.setDescription("Math3");
//
//        Course               course4 = createObject.createCourse(4);
//        SemesterCourse       semesterCourse4 = createObject.createSemesterCourse(4l,course4);
//        Session              newsession4 = createObject.createSession(4l,semesterCourse4,4);
//        semesterCourse4.setSessions(Arrays.asList(newsession4));
//        course4.setSemesterCourses(Arrays.asList(semesterCourse4));
//        course4.setName("Math4");
//        course4.setCode("Math4");
//        course4.setDescription("Math4");
//
//        CoursePrerequisiteId coursePrerequisiteId = CoursePrerequisiteId.builder().course(course4)
//                .prerequisite(course3).build();
//        CoursePrerequisite coursePrerequisite = CoursePrerequisite.builder()
//                .id(coursePrerequisiteId)
//                .build();
//        course4.setPrerequisites(Arrays.asList(coursePrerequisite));
//
//        Course               course5 = createObject.createCourse(5);
//        SemesterCourse       semesterCourse5 = createObject.createSemesterCourse(5l,course5);
//        Session              newsession5 = createObject.createSession(5l,semesterCourse5,5);
//        semesterCourse5.setSessions(Arrays.asList(newsession5));
//        course5.setSemesterCourses(Arrays.asList(semesterCourse5));
//        course5.setName("DS");
//        course5.setCode("DS");
//        course5.setDescription("DS");
//
//        Course               course6 = createObject.createCourse(6);
//        SemesterCourse       semesterCourse6 = createObject.createSemesterCourse(6l,course6);
//        Session              newsession6 = createObject.createSession(6l,semesterCourse4,6);
//        semesterCourse6.setSessions(Arrays.asList(newsession6));
//        course6.setSemesterCourses(Arrays.asList(semesterCourse6));
//        course6.setName("Electronics");
//        course6.setCode("Electronics");
//        course6.setDescription("Electronics");
//
//        List<SemesterCourse> semCourses = Arrays.asList(semesterCourse3,semesterCourse5,semesterCourse6);
//
//        Mockito.when(studentProfileRepository.findById(1))
//                .thenReturn(Optional.ofNullable(studentProfile1));
//
//        Mockito.when(semesterCourseRepository.findAllByCourseId(3))
//                .thenReturn(Arrays.asList(semesterCourse3));
//        Mockito.when(semesterCourseRepository.findAllByCourseId(5))
//                .thenReturn(Arrays.asList(semesterCourse5));
//        Mockito.when(semesterCourseRepository.findAllByCourseId(6))
//                .thenReturn(Arrays.asList(semesterCourse6));
//        Mockito.when(courseRepository.findAll())
//                .thenReturn(Arrays.asList(course3,course4,course5,course6));
//
//        List<SemesterCourse> retsemCourses = registrationService.getAvailableCourses(1,"Math");
//
//        assertEquals(semCourses,retsemCourses);
//    }
//
//    @Test
//    void checkOut() {
//
//        Course               course3 = createObject.createCourse(3);
//        SemesterCourse       semesterCourse3 = createObject.createSemesterCourse(3l,course3);
//        Session              newsession3 = createObject.createSession(3l,semesterCourse3,3);
//        semesterCourse3.setSessions(Arrays.asList(newsession3));
//        course3.setSemesterCourses(Arrays.asList(semesterCourse3));
//
//        Course               course4 = createObject.createCourse(4);
//        SemesterCourse       semesterCourse4 = createObject.createSemesterCourse(4l,course4);
//        Session              newsession4 = createObject.createSession(4l,semesterCourse4,4);
//        semesterCourse3.setSessions(Arrays.asList(newsession4));
//        course4.setSemesterCourses(Arrays.asList(semesterCourse4));
//
//
//
//        Mockito.when(semesterCourseRepository.findById(3l))
//                .thenReturn(Optional.ofNullable(semesterCourse3));
//        Mockito.when(semesterCourseRepository.findById(4l))
//                .thenReturn(Optional.ofNullable(semesterCourse4));
//        Mockito.when(studentProfileRepository.findById(1))
//                .thenReturn(Optional.ofNullable(studentProfile1));
//
//        boolean res = registrationService.checkOut(1,Arrays.asList(3l,4l));
//        assertEquals(true,res);
//
//    }
//
//    @Test
//    void showFees() {
//        assert (true);
//    }
//
//    @Test
//    void getPaymentDeadLine() {
//        assert (true);
//    }
//}