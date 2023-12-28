package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.SessionRequest;
import com.centralstudenthub.Model.Response.sessions.SessionResponse;
import com.centralstudenthub.Model.SessionType;
import com.centralstudenthub.entity.sessions.Session;
import com.centralstudenthub.entity.sessions.location.Location;
import com.centralstudenthub.entity.sessions.location.LocationId;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.LocationRepository;
import com.centralstudenthub.repository.SemesterCourseRepository;
import com.centralstudenthub.repository.SessionRepository;
import com.centralstudenthub.repository.TeachingStaffProfileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionServiceTest {
    @Autowired
    private SessionService sessionService;

    @MockBean
    private SemesterCourseRepository semesterCourseRepository;

    @MockBean
    private TeachingStaffProfileRepository teachingStaffProfileRepository;

    @MockBean
    private SessionRepository sessionRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Test
    public void addSessionTest() throws NotFoundException {
        Random random = new Random();
        for (int i = 0; i < 1000; i++){
            int coin1 = random.nextInt(2);
            int coin2 = random.nextInt(2);
            int coin3 = random.nextInt(2);

            SessionRequest sessionRequest = SessionRequest.builder().semCourseId(random.nextLong())
                    .teacherId(random.nextInt()).period(random.nextInt()).weekDay("Monday").sessionType(SessionType.LECTURE)
                    .building(random.nextInt()).room(random.nextInt()).build();

            SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(sessionRequest.getSemCourseId()).build();
            TeachingStaffProfile teachingStaffProfile = TeachingStaffProfile.builder().teacherId(sessionRequest.getTeacherId()).build();
            LocationId locationId = LocationId.builder().building(sessionRequest.getBuilding()).room(sessionRequest.getRoom()).build();
            Location location = Location.builder().id(locationId).build();

            if (coin1 == 0)
                Mockito.when(semesterCourseRepository.findById(sessionRequest.getSemCourseId())).thenReturn(Optional.of(semesterCourse));
            else
                Mockito.when(semesterCourseRepository.findById(sessionRequest.getSemCourseId())).thenReturn(Optional.empty());

            if (coin2 == 0)
                Mockito.when(teachingStaffProfileRepository.findById(sessionRequest.getTeacherId())).thenReturn(Optional.of(teachingStaffProfile));
            else
                Mockito.when(teachingStaffProfileRepository.findById(sessionRequest.getTeacherId())).thenReturn(Optional.empty());

            if (coin3 == 0)
                Mockito.when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));
            else
                Mockito.when(locationRepository.findById(locationId)).thenReturn(Optional.empty());

            if (coin1 == 1 || coin2 == 1 || coin3 == 1) {
                assertThrows(NotFoundException.class, () -> sessionService.addSession(sessionRequest));
            } else {
                Mockito.when(sessionRepository.save(Mockito.any())).thenReturn(null);
                assertTrue(sessionService.addSession(sessionRequest));
            }
        }
    }

    @Test
    public void getSessionTest() throws NotFoundException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int coin = random.nextInt(2);
            SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(random.nextLong()).build();
            TeachingStaffProfile teachingStaffProfile = TeachingStaffProfile.builder().teacherId(random.nextInt()).build();
            LocationId locationId = LocationId.builder().building(random.nextInt()).room(random.nextInt()).build();
            Location location = Location.builder().id(locationId).build();
            Session session = Session.builder().sessionId(random.nextLong()).period(random.nextInt()).weekDay("Monday")
                    .sessionType(SessionType.LECTURE).semCourse(semesterCourse).teacher(teachingStaffProfile).location(location).build();

            if (coin == 0) {
                Mockito.when(sessionRepository.findById(session.getSessionId())).thenReturn(Optional.of(session));
                assertEquals(session.toResponse(), sessionService.getSession(session.getSessionId()));
            }
            else {
                Mockito.when(sessionRepository.findById(session.getSessionId())).thenReturn(Optional.empty());
                assertThrows(NotFoundException.class, () -> sessionService.getSession(session.getSessionId()));
            }
        }
    }

    @Test
    public void getSessionsTest() throws NotFoundException {
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            int coin = random.nextInt(2);
            SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(random.nextLong()).build();
            List<Object[]> sessions = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                Object[] session = new Object[4];
                session[0] = random.nextLong();
                session[1] = random.nextInt();
                session[2] = "Monday";
                session[3] = SessionType.LECTURE;
                sessions.add(session);
            }
            List<SessionResponse> sessionResponses = new ArrayList<>();
            for (Object[] session : sessions) {
                sessionResponses.add(SessionResponse.builder().id((Long) session[0]).period((Integer) session[1])
                        .weekDay((String) session[2]).sessionType((SessionType) session[3]).build());
            }

            if (coin == 0) {
                Mockito.when(semesterCourseRepository.findById(semesterCourse.getSemCourseId())).thenReturn(Optional.of(semesterCourse));
                Mockito.when(sessionRepository.findAllSessionsBySemCourseId(semesterCourse.getSemCourseId())).thenReturn(sessions);
                assertEquals(sessionResponses, sessionService.getSessions(semesterCourse.getSemCourseId()));
            }
            else {
                Mockito.when(semesterCourseRepository.findById(semesterCourse.getSemCourseId())).thenReturn(Optional.empty());
                assertThrows(NotFoundException.class, () -> sessionService.getSessions(1L));
            }
        }
    }

    @Test
    public void updateSessionTest() throws NotFoundException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int coin = random.nextInt(2);
            SessionRequest sessionUpdates = SessionRequest.builder().period(random.nextInt()).weekDay("Monday")
                    .sessionType(SessionType.LECTURE).build();
            Session session = Session.builder().sessionId(random.nextLong()).period(random.nextInt()).weekDay("Monday")
                    .sessionType(SessionType.LECTURE).build();

            if (coin == 0) {
                Mockito.when(sessionRepository.findById(session.getSessionId())).thenReturn(Optional.of(session));
                Mockito.when(sessionRepository.save(session)).thenReturn(null);
                assertTrue(sessionService.updateSession(session.getSessionId(), sessionUpdates));
            }
            else {
                Mockito.when(sessionRepository.findById(session.getSessionId())).thenReturn(Optional.empty());
                assertThrows(NotFoundException.class, () -> sessionService.updateSession(session.getSessionId(), sessionUpdates));
            }
        }
    }

    @Test
    public void deleteSessionTest() throws NotFoundException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int coin = random.nextInt(2);
            Session session = Session.builder().sessionId(random.nextLong()).period(random.nextInt()).weekDay("Monday")
                    .sessionType(SessionType.LECTURE).build();

            if (coin == 0) {
                Mockito.when(sessionRepository.findById(session.getSessionId())).thenReturn(Optional.of(session));
                Mockito.doNothing().when(sessionRepository).delete(session);
                assertTrue(sessionService.deleteSession(session.getSessionId()));
            }
            else {
                Mockito.when(sessionRepository.findById(session.getSessionId())).thenReturn(Optional.empty());
                assertThrows(NotFoundException.class, () -> sessionService.deleteSession(session.getSessionId()));
            }
        }
    }

}