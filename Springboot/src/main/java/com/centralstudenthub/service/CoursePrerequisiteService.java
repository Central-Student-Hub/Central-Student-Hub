package com.centralstudenthub.service;

import com.centralstudenthub.Model.Response.CourseResponse;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisite;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisiteId;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.exception.DatabaseLogicalConstraintException;
import com.centralstudenthub.repository.CoursePrerequisiteRepository;
import com.centralstudenthub.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CoursePrerequisiteService {
    private final CourseRepository courseRepository;
    private final CoursePrerequisiteRepository coursePrerequisiteRepository;

    @Autowired
    public CoursePrerequisiteService(CourseRepository courseRepository,
                                     CoursePrerequisiteRepository coursePrerequisiteRepository) {
        this.courseRepository = courseRepository;
        this.coursePrerequisiteRepository = coursePrerequisiteRepository;
    }

    public boolean addCoursePrerequisite(int courseId, int prerequisiteId) throws NotFoundException,
            DatabaseLogicalConstraintException, AlreadyExistsException {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Course> prerequisite = courseRepository.findById(prerequisiteId);
        if (course.isEmpty() || prerequisite.isEmpty())
            throw new NotFoundException("Course or Prerequisite not found");

        if (Objects.equals(course.get().getCourseId(), prerequisite.get().getCourseId()))
            throw new DatabaseLogicalConstraintException("Course and Prerequisite cannot be same");

        CoursePrerequisiteId coursePrerequisiteId = CoursePrerequisiteId.builder().course(course.get())
                .prerequisite(prerequisite.get()).build();

        if (coursePrerequisiteRepository.existsById(coursePrerequisiteId))
            throw new AlreadyExistsException("Course Prerequisite already exists");

        coursePrerequisiteRepository.save(CoursePrerequisite.builder().id(coursePrerequisiteId).build());
        return true;
    }

    public List<CourseResponse> getCoursePrerequisites(int courseId) throws NotFoundException {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty())
            throw new NotFoundException("Course not found");

        List<Integer> coursePrerequisitesIds = coursePrerequisiteRepository.findAllPrerequisites(courseId);
        List<CourseResponse> coursePrerequisites = new ArrayList<>();
        for (Integer coursePrerequisiteId : coursePrerequisitesIds) {
            course = courseRepository.findById(coursePrerequisiteId);
            if (course.isEmpty())
                continue;
            coursePrerequisites.add(course.get().toCourseResponse());
        }
        return coursePrerequisites;
    }

    public boolean deleteCoursePrerequisite(int courseId, int prerequisiteId) throws NotFoundException {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Course> prerequisite = courseRepository.findById(prerequisiteId);
        if (course.isEmpty() || prerequisite.isEmpty())
            throw new NotFoundException("Course or Prerequisite not found");

        CoursePrerequisiteId coursePrerequisiteId = CoursePrerequisiteId.builder().course(course.get())
                .prerequisite(prerequisite.get()).build();

        Optional<CoursePrerequisite> coursePrerequisite = coursePrerequisiteRepository.findById(coursePrerequisiteId);
        if (coursePrerequisite.isEmpty())
            throw new NotFoundException("Course Prerequisite does not exists");

        coursePrerequisiteRepository.delete(coursePrerequisite.get());
        return true;
    }
}
