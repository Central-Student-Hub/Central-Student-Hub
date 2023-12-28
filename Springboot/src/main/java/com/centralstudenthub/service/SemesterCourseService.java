package com.centralstudenthub.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.centralstudenthub.Model.Response.sessions.LocationResponse;
import com.centralstudenthub.Model.Response.teacher_profile.TeachingStaffProfileModel;
import com.centralstudenthub.Model.StudentCourseResponses.StdCourseRes;
import com.centralstudenthub.entity.sessions.location.Location;
import com.centralstudenthub.entity.sessions.location.LocationId;
import com.centralstudenthub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centralstudenthub.Model.Request.SemesterCourseRequest;
import com.centralstudenthub.Model.Response.student_profile.course.semester_courses.SemesterCourseResponse;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.exception.NotFoundException;

@Service
public class SemesterCourseService {
    private final CourseRepository courseRepository;
    private final SemesterCourseRepository semesterCourseRepository;
    private final LocationRepository locationRepository;
    private final SessionRepository sessionRepository;
    private final RegistrationRepository registrationRepository;
    private final UserProfileService userProfileService;

    @Autowired
    public SemesterCourseService(CourseRepository courseRepository, SemesterCourseRepository semesterCourseRepository,
                                 LocationRepository locationRepository, SessionRepository sessionRepository,
                                 RegistrationRepository registrationRepository, UserProfileService userProfileService) {
        this.semesterCourseRepository = semesterCourseRepository;
        this.courseRepository = courseRepository;
        this.locationRepository = locationRepository;
        this.sessionRepository = sessionRepository;
        this.registrationRepository = registrationRepository;
        this.userProfileService = userProfileService;
    }

    public Long addSemesterCourse(SemesterCourseRequest semesterCourseRequest) throws NotFoundException {
        Optional<Course> course = courseRepository.findById(semesterCourseRequest.getCourseId());
        if (course.isEmpty()) throw new NotFoundException("Course not found");
        SemesterCourse semesterCourse = SemesterCourse.builder().course(course.get())
                .semester(semesterCourseRequest.getSemester())
                .maxSeats(semesterCourseRequest.getMaxSeats())
                .build();
        semesterCourseRepository.save(semesterCourse);
        return semesterCourse.getSemCourseId();
    }

    public SemesterCourseResponse getSemesterCourse(Long id) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty()) throw new NotFoundException("Semester course not found");
        return semesterCourse.get().toResponse();

    }

    public List<SemesterCourseResponse> getSemesterCourses() throws NotFoundException {
        List<SemesterCourse> semesterCourses = semesterCourseRepository.findAll();
        if (semesterCourses.isEmpty()) throw new NotFoundException("Semester courses not found");
        return semesterCourses.stream().map(this::getSemesterCourseResponse).collect(Collectors.toList());
    }

    public boolean updateSemesterCourse(Long id, SemesterCourseRequest semesterCourseUpdates) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty()) throw new NotFoundException("Semester course not found");
        semesterCourse.get().setSemester(semesterCourseUpdates.getSemester());
        semesterCourse.get().setMaxSeats(semesterCourseUpdates.getMaxSeats());
        semesterCourseRepository.save(semesterCourse.get());
        return true;
    }

    public boolean deleteSemesterCourse(Long id) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty()) throw new NotFoundException("Semester course not found");
        semesterCourseRepository.delete(semesterCourse.get());
        return true;
    }

    public boolean deleteAllSemesterCourses() {
        semesterCourseRepository.deleteAll();
        return true;
    }

    public SemesterCourseResponse getSemesterCourseResponse(SemesterCourse semCourse) {
        String code = semesterCourseRepository.findCourseCodeBySemesterCourseId(semCourse.getSemCourseId());
        String name = semesterCourseRepository.findCourseNameBySemesterCourseId(semCourse.getSemCourseId());
        String description = semesterCourseRepository.findCourseDescriptionBySemesterCourseId(semCourse.getSemCourseId());
        int creditHours = semesterCourseRepository.findCreditHoursBySemesterCourseId(semCourse.getSemCourseId());

        SemesterCourseResponse response = semCourse.toResponse();
        response.setCode(code);
        response.setName(name);
        response.setDescription(description);
        response.setCreditHours(creditHours);

        return response;
    }

    public List<LocationResponse> getAllLocations() {
        return locationRepository.findAll().stream().map(Location::toResponse).toList();
    }

    public boolean addLocation(LocationResponse request) {
        LocationId id = LocationId.builder().building(request.building()).room(request.room()).build();
        Optional<Location> existingLocation = locationRepository.findById(id);
        if (existingLocation.isPresent()) return false;
        Location location = Location.builder().id(id).capacity(request.capacity()).build();
        locationRepository.save(location);
        return true;
    }

    public List<StdCourseRes> getSemCourseIdsByStudentId(int id) {
        List<Optional<SemesterCourse>> semCourses = registrationRepository.findAllSemesterCoursesIdsByStudentId(id)
                .stream().map(semesterCourseRepository::findById).toList();

        if(semCourses.isEmpty())return null;

        return semCourses.stream().map(
                semesterCourse -> {
                    if (semesterCourse.isEmpty()) return null;
                    SemesterCourse semCourse = semesterCourse.get();

                    TeachingStaffProfileModel teachingStaffProfileModel = userProfileService.getTeachingStaffProfileInfo(
                            sessionRepository.findTeacherIdBySemCourseId(semCourse.getSemCourseId()));

                    return StdCourseRes.builder()
                            .teacherId(teachingStaffProfileModel.getId())
                            .teacherFirstName(teachingStaffProfileModel.getFirstName())
                            .teacherLastName(teachingStaffProfileModel.getLastName())
                            .semCourseId(semCourse.getSemCourseId())
                            .semCourseName(semCourse.getCourse().getName())
                            .semCourseCode(semCourse.getCourse().getCode())
                            .semCourseDescription(semCourse.getCourse().getDescription())
                            .semCourseCreditHours(semCourse.getCourse().getCreditHours())
                            .build();
                }
        ).toList();
    }
}
