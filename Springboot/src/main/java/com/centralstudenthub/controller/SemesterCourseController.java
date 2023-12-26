package com.centralstudenthub.controller;

import com.centralstudenthub.Model.LocationModel;
import com.centralstudenthub.Model.Request.SemesterCourseRequest;
import com.centralstudenthub.Model.Request.SessionRequest;
import com.centralstudenthub.Model.Response.SemesterCourseResponse;
import com.centralstudenthub.Model.Response.SessionResponse;
import com.centralstudenthub.Model.Semester;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.service.FeedbackService;
import com.centralstudenthub.service.MaterialPathService;
import com.centralstudenthub.service.SemesterCourseService;
import com.centralstudenthub.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/SemesterCourse")
public class SemesterCourseController {
    private final Logger logger = Logger.getLogger(CourseController.class.getName());

    private final SemesterCourseService semesterCourseService;
    private final MaterialPathService materialPathService;
    private final FeedbackService feedbackService;
    private final SessionService sessionService;

    @Autowired
    public SemesterCourseController(SemesterCourseService semesterCourseService, MaterialPathService materialPathService,
                                    FeedbackService feedbackService, SessionService sessionService) {
        this.semesterCourseService = semesterCourseService;
        this.materialPathService = materialPathService;
        this.feedbackService = feedbackService;
        this.sessionService = sessionService;
    }

    @PostMapping("/addSemesterCourse")
    public Long addSemesterCourse(@RequestBody SemesterCourseRequest semesterCourse) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: addSemesterCourse");
        return semesterCourseService.addSemesterCourse(semesterCourse);
    }

    @GetMapping("/getSemesterCourse/{id}")
    public SemesterCourseResponse getSemesterCourse(@PathVariable("id") Long id) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: getSemesterCourse");
        return semesterCourseService.getSemesterCourse(id);
    }

    @GetMapping("/getSemesterCourses")
    public List<SemesterCourseResponse> getSemesterCourses() throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: getSemesterCourses");
        return semesterCourseService.getSemesterCourses();
    }

    @PutMapping("/updateSemesterCourse/{id}")
    public boolean updateSemesterCourse(@PathVariable("id") Long id,
                                        @RequestBody SemesterCourseRequest semesterCourseUpdates) throws
            NotFoundException {
        logger.info("Class: SemesterCourseController, Method: updateSemesterCourse");
        return semesterCourseService.updateSemesterCourse(id, semesterCourseUpdates);
    }

    @DeleteMapping("/deleteSemesterCourse/{id}")
    public boolean deleteSemesterCourse(@PathVariable("id") Long id) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: deleteSemesterCourse");
        return semesterCourseService.deleteSemesterCourse(id);
    }

    @DeleteMapping("/deleteAllSemesterCourses")
    public boolean deleteAllSemesterCourses() {
        logger.info("Class: SemesterCourseController, Method: deleteAllSemesterCourses");
        return semesterCourseService.deleteAllSemesterCourses();
    }

    @PostMapping("/addMaterialPath")
    public boolean addMaterialPath(@RequestParam Long id, @RequestParam String materialPath) throws
            AlreadyExistsException, NotFoundException {
        logger.info("Class: SemesterCourseController, Method: addMaterialPath");
        return materialPathService.addMaterialPath(id, materialPath);
    }

    @GetMapping("/getMaterialPaths/{id}")
    public List<String> getMaterialPaths(@PathVariable("id") Long id) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: getMaterialPaths");
        return materialPathService.getMaterialPaths(id);
    }

    @PutMapping("/updateMaterialPath/{id}/{oldPath}")
    public boolean updateMaterialPath(@PathVariable("id") Long id, @PathVariable("oldPath") String oldPath,
                                      @RequestParam String newPath) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: updateMaterialPath");
        return materialPathService.updateMaterialPath(id, oldPath, newPath);
    }

    @DeleteMapping("/deleteMaterialPath/{id}/{materialPath}")
    public boolean deleteMaterialPath(@PathVariable("id") Long id, @PathVariable("materialPath") String materialPath)
            throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: deleteMaterialPath");
        return materialPathService.deleteMaterialPath(id, materialPath);
    }

    @PostMapping("/addFeedback")
    public Long addFeedback(@RequestParam Long id, @RequestParam String content) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: addFeedback");
        return feedbackService.addFeedback(id, content);
    }

    @GetMapping("/getFeedback/{feedbackId}")
    public String getFeedback(@PathVariable("feedbackId") Long feedbackId) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: getFeedback");
        return feedbackService.getFeedback(feedbackId);
    }

    @GetMapping("/getFeedbacks/{id}")
    public List<String> getFeedbacks(@PathVariable("id") Long id) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: getFeedbacks");
        return feedbackService.getFeedbacks(id);
    }

    @PutMapping("/updateFeedback/{feedbackId}")
    public boolean updateFeedback(@PathVariable("feedbackId") Long feedbackId, @RequestParam String newContent) throws
            NotFoundException {
        logger.info("Class: SemesterCourseController, Method: updateFeedback");
        return feedbackService.updateFeedback(feedbackId, newContent);
    }

    @DeleteMapping("/deleteFeedback/{feedbackId}")
    public boolean deleteFeedback(@PathVariable("feedbackId") Long feedbackId) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: deleteFeedback");
        return feedbackService.deleteFeedback(feedbackId);
    }

    @PostMapping("/addSession")
    public boolean addSession(@RequestBody SessionRequest session) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: addSession");
        return sessionService.addSession(session);
    }

    @GetMapping("/getSession/{sessionId}")
    public SessionResponse getSession(@PathVariable("sessionId") Long sessionId) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: getSession");
        return sessionService.getSession(sessionId);
    }

    @GetMapping("/getSessions/{id}")
    public List<SessionResponse> getSessions(@PathVariable("id") Long id) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: getSessions");
        return sessionService.getSessions(id);
    }

    @PutMapping("/updateSession/{sessionId}")
    public boolean updateSession(@PathVariable("sessionId") Long sessionId, @RequestBody SessionRequest sessionUpdates)
            throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: updateSession");
        return sessionService.updateSession(sessionId, sessionUpdates);
    }

    @DeleteMapping("/deleteSession/{sessionId}")
    public boolean deleteSession(@PathVariable("sessionId") Long sessionId) throws NotFoundException {
        logger.info("Class: SemesterCourseController, Method: deleteSession");
        return sessionService.deleteSession(sessionId);
    }

    @GetMapping("/location")
    public List<LocationModel> getAllLocations() {
        return semesterCourseService.getAllLocations();
    }
}
