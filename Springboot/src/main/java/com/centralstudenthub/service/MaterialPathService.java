package com.centralstudenthub.service;

import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths.CourseMaterialPath;
import com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths.CourseMaterialPathId;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.CourseMaterialPathRepository;
import com.centralstudenthub.repository.SemesterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialPathService {
    private final SemesterCourseRepository semesterCourseRepository;
    private final CourseMaterialPathRepository courseMaterialPathRepository;

    @Autowired
    public MaterialPathService(SemesterCourseRepository semesterCourseRepository,
                                 CourseMaterialPathRepository courseMaterialPathRepository) {
        this.semesterCourseRepository = semesterCourseRepository;
        this.courseMaterialPathRepository = courseMaterialPathRepository;
    }

    private CourseMaterialPath getMaterialPathEntity(Long id, String materialPath) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester course not found");

        CourseMaterialPathId courseMaterialPathId = CourseMaterialPathId.builder()
                .semCourse(semesterCourse.get())
                .materialPath(materialPath)
                .build();

        Optional<CourseMaterialPath> courseMaterialPath = courseMaterialPathRepository.findById(courseMaterialPathId);
        if (courseMaterialPath.isEmpty())
            throw new NotFoundException("Material path not found");

        return courseMaterialPath.get();
    }

    public boolean addMaterialPath(Long id, String materialPath) throws NotFoundException, AlreadyExistsException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester course not found");

        CourseMaterialPathId courseMaterialPathId = CourseMaterialPathId.builder()
                .semCourse(semesterCourse.get())
                .materialPath(materialPath)
                .build();

        if (courseMaterialPathRepository.existsById(courseMaterialPathId))
            throw new AlreadyExistsException("Material path already exists");

        courseMaterialPathRepository.save(CourseMaterialPath.builder().id(courseMaterialPathId).build());
        return true;
    }

    public List<String> getMaterialPaths(Long id) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester course not found");

        return courseMaterialPathRepository.findAllMaterialPathsBySemCourseId(id);
    }

    public boolean updateMaterialPath(Long id, String oldPath, String newPath) throws NotFoundException {
        if (newPath == null) return false;
        CourseMaterialPath courseMaterialPath = getMaterialPathEntity(id, oldPath);
        courseMaterialPath.getId().setMaterialPath(newPath);
        courseMaterialPathRepository.save(courseMaterialPath);
        return true;
    }

    public boolean deleteMaterialPath(Long id, String materialPath) throws NotFoundException {
        courseMaterialPathRepository.delete(getMaterialPathEntity(id, materialPath));
        return true;
    }

}
