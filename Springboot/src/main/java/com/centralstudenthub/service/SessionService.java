package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.SessionRequest;
import com.centralstudenthub.Model.Response.SessionResponse;
import com.centralstudenthub.Model.SessionModel;
import com.centralstudenthub.Model.SessionType;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.Session;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.SemesterCourseRepository;
import com.centralstudenthub.repository.SessionRepository;
import com.centralstudenthub.repository.TeachingStaffProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    private final SemesterCourseRepository semesterCourseRepository;
    private final TeachingStaffProfileRepository teachingStaffProfileRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SemesterCourseRepository semesterCourseRepository,
                          TeachingStaffProfileRepository teachingStaffProfileRepository,
                          SessionRepository sessionRepository) {
        this.semesterCourseRepository = semesterCourseRepository;
        this.teachingStaffProfileRepository = teachingStaffProfileRepository;
        this.sessionRepository = sessionRepository;
    }

    public Long addSession(SessionRequest sessionRequest) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(sessionRequest.getSemCourseId());
        Optional<TeachingStaffProfile> teachingStaffProfile =
                teachingStaffProfileRepository.findById(sessionRequest.getTeacherId());
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester Course not found");
        if (teachingStaffProfile.isEmpty())
            throw new NotFoundException("Teaching Staff Profile not found");

        Session session = Session.builder().period(sessionRequest.getPeriod()).weekDay(sessionRequest.getWeekDay())
                .sessionType(sessionRequest.getSessionType()).semCourse(semesterCourse.get())
                .teacher(teachingStaffProfile.get()).build();
        sessionRepository.save(session);
        return session.getSessionId();
    }

    public SessionModel getSession(Long sessionId) throws NotFoundException {
        Optional<Session> session = sessionRepository.findById(sessionId);
        if (session.isEmpty())
            throw new NotFoundException("Session not found");

        return session.get().toModel();
    }

    public List<SessionResponse> getSessions(Long id) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester Course not found");

        List<Object[]> sessions = sessionRepository.findAllSessionsBySemCourseId(id);
        List<SessionResponse> sessionResponses = new ArrayList<>();
        for (Object[] session : sessions) {
            sessionResponses.add(SessionResponse.builder().sessionId((Long) session[0]).period((Integer) session[1])
                    .weekDay((String) session[2]).sessionType((SessionType) session[3]).build());
        }
        return sessionResponses;
    }

    public boolean updateSession(Long sessionId, SessionRequest sessionUpdates) throws NotFoundException {
        Optional<Session> session = sessionRepository.findById(sessionId);
        if (session.isEmpty())
            throw new NotFoundException("Session not found");

        if (sessionUpdates.getPeriod() != null)
            session.get().setPeriod(sessionUpdates.getPeriod());
        if (sessionUpdates.getWeekDay() != null)
            session.get().setWeekDay(sessionUpdates.getWeekDay());
        if (sessionUpdates.getSessionType() != null)
            session.get().setSessionType(sessionUpdates.getSessionType());
        sessionRepository.save(session.get());
        return true;
    }

    public boolean deleteSession(Long sessionId) throws NotFoundException {
        Optional<Session> session = sessionRepository.findById(sessionId);
        if (session.isEmpty())
            throw new NotFoundException("Session not found");

        sessionRepository.delete(session.get());
        return true;
    }
}
