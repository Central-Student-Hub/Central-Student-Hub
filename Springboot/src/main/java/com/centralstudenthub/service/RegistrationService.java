package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.AddCourseToCartRequest;
import com.centralstudenthub.Model.Response.student_profile.course.semester_courses.SemesterCourseResponse;
import com.centralstudenthub.Model.Response.sessions.SessionResponse;
import com.centralstudenthub.Validator.RegistrationValidator;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.Registration;
import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.RegistrationId;
import com.centralstudenthub.exception.NullCourseException;
import com.centralstudenthub.exception.NullRegisteredSessionsException;
import com.centralstudenthub.exception.NullSemesterCourseException;
import com.centralstudenthub.exception.NullStudentProfileException;
import com.centralstudenthub.repository.CoursePrerequisiteRepository;
import com.centralstudenthub.repository.RegistrationRepository;
import com.centralstudenthub.repository.SemesterCourseRepository;
import com.centralstudenthub.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationValidator registrationValidator;

    @Autowired
    private SearchService searchService;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private SemesterCourseRepository semesterCourseRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CoursePrerequisiteRepository coursePrerequisiteRepository;

    private final Double HOURLY_FEES = 100.0;
    private final long expiration = 7776000000L;

    public boolean addCourseToCart(AddCourseToCartRequest request, int studentId)
            throws NullStudentProfileException, NullCourseException, NullSemesterCourseException, NullRegisteredSessionsException {

        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(studentId);
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(request.getCourseId());
        if (studentProfile.isEmpty() || semesterCourse.isEmpty()) return false;
        Course course = semesterCourse.get().getCourse();

        boolean creditHoursSatisfied = registrationValidator.validateCreditHoursLimit(studentProfile.get(), course, request.getCreditHours());
        boolean seatsAvailable = registrationValidator.validateSeatsAvailable(semesterCourse.get());
        boolean nonConflictingSessionTimes = registrationValidator.validateSessionTimes(
                request.getSessions().stream().map(SessionResponse::toEntity).toList(),
                request.getNewSessions().stream().map(SessionResponse::toEntity).toList()
        );

        return creditHoursSatisfied && seatsAvailable && nonConflictingSessionTimes;
    }

    public List<SemesterCourseResponse> getAvailableCourses(int studentId, String searchPhrase) {

        List<Course> allCourses = searchService.filterCourses(searchPhrase);

        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(studentId);
        if (studentProfile.isEmpty()) return null;

        List<Course> filteredCourses = allCourses.stream().filter(course -> {
            try {
                return registrationValidator.validatePrerequisites(studentProfile.get(), course).equals("Prerequisites Satisfied");
            } catch (NullStudentProfileException | NullCourseException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        List<Integer> courseIds = filteredCourses.stream().map(Course::getCourseId).toList();
        List<SemesterCourse> semesterCourses = new ArrayList<>();
        for (Integer courseId : courseIds)
            semesterCourses.addAll(semesterCourseRepository.findAllByCourseId(courseId));

        List<SemesterCourseResponse> semesterCourseResponses = semesterCourses.stream().map(SemesterCourse::toResponse).toList();

        semesterCourseResponses.forEach(semesterCourseResponse -> {
            int remainingSeats = semesterCourseResponse.getMaxSeats() - registrationRepository.findCountBySemCourse(semesterCourseResponse.getSemCourseId());
            semesterCourseResponse.setRemainingSeats(remainingSeats);
        });

        semesterCourseResponses.forEach(semesterCourseResponse -> {
            List<String> prerequisiteCodes = coursePrerequisiteRepository.findCodesByCourseId(
                    semesterCourseRepository.findCourseIdBySemesterCourseId(semesterCourseResponse.getSemCourseId())
            );
            semesterCourseResponse.setPrerequisitesCodes(prerequisiteCodes);
        });

        return semesterCourses.stream().map(SemesterCourse::toResponse).toList();
    }

    public boolean checkOut(int studentId, List<Long> semCourseIds) {
        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(studentId);
        List<Registration> registrations = new ArrayList<>();
        if (studentProfile.isEmpty()) return false;
        for (Long courseid : semCourseIds) {
            Optional<SemesterCourse> semCourse = semesterCourseRepository.findById(courseid);
            if (semCourse.isEmpty()) return false;

            RegistrationId registrationId = RegistrationId.builder()
                    .semCourse(semCourse.get())
                    .student(studentProfile.get())
                    .build();
            Registration registration = Registration.builder()
                    .id(registrationId)
                    .paymentFees(semCourse.get().getCourse().getCreditHours() * HOURLY_FEES)
                    .paymentDeadline(new Date(System.currentTimeMillis() + expiration))
                    .build();

            registrations.add(registration);
        }
        registrationRepository.saveAll(registrations);
        return true;
    }

    public double showFees(int studentId) {

        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(studentId);
        if (studentProfile.isEmpty()) return 0;

        return registrationRepository.findFeesByStudentID(studentId);
    }

    public Date getPaymentDeadLine(int studentId) {
        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(studentId);
        if (studentProfile.isEmpty()) return new Date();
        return registrationRepository.findPaymentDeadlineByStudentID(studentId);
    }
}
