package com.centralstudenthub.service;

import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisite;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisiteId;
import com.centralstudenthub.exception.CourseAlreadyExistsException;
import com.centralstudenthub.exception.CourseNotFoundException;
import com.centralstudenthub.exception.DatabaseLogicalConstraintException;
import com.centralstudenthub.repository.CoursePrerequisiteRepository;
import com.centralstudenthub.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean addCoursePrerequisite(int courseId, int prerequisiteId) throws CourseNotFoundException,
            DatabaseLogicalConstraintException, CourseAlreadyExistsException {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Course> prerequisite = courseRepository.findById(prerequisiteId);
        if (course.isEmpty() || prerequisite.isEmpty())
            throw new CourseNotFoundException("Course or Prerequisite not found");

        if (Objects.equals(course.get().getCourseId(), prerequisite.get().getCourseId()))
            throw new DatabaseLogicalConstraintException("Course and Prerequisite cannot be same");

        CoursePrerequisiteId coursePrerequisiteId = CoursePrerequisiteId.builder().course(course.get())
                .prerequisite(prerequisite.get()).build();

        Optional<CoursePrerequisite> coursePrerequisite = coursePrerequisiteRepository.findById(coursePrerequisiteId);
        if (coursePrerequisite.isPresent())
            throw new CourseAlreadyExistsException("Course Prerequisite already exists");

        coursePrerequisiteRepository.save(CoursePrerequisite.builder().id(coursePrerequisiteId).build());
        return true;
    }

    public List<Integer> getCoursePrerequisites(int id) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty())
            throw new CourseNotFoundException("Course not found");

        return coursePrerequisiteRepository.findAllPrerequisites(id);
    }
}
