package com.centralstudenthub.service;

import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths.CourseMaterialPath;
import com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths.CourseMaterialPathId;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.CourseMaterialPathRepository;
import com.centralstudenthub.repository.SemesterCourseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MaterialPathServiceTest {
    @Autowired
    private MaterialPathService materialPathService;

    @MockBean
    private SemesterCourseRepository semesterCourseRepository;

    @MockBean
    private CourseMaterialPathRepository courseMaterialPathRepository;

    @Test
    public void addMaterialPath_semCourseId_NotInDB() {
        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> materialPathService.addMaterialPath(1L, "path"));
    }

    @Test
    public void addMaterialPath_semCourseId_materialPathId_InDB() {
        SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(1L).build();

        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(semesterCourse));
        Mockito.when(courseMaterialPathRepository.existsById(Mockito.any(CourseMaterialPathId.class))).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> materialPathService.addMaterialPath(1L, "path"));
    }

    @Test
    public void addMaterialPath_semCourseId_InDB_materialPathId_NotInDB() throws AlreadyExistsException, NotFoundException {
        SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(1L).build();

        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(semesterCourse));
        Mockito.when(courseMaterialPathRepository.existsById(Mockito.any(CourseMaterialPathId.class))).thenReturn(false);
        Mockito.when(courseMaterialPathRepository.save(Mockito.any())).thenReturn(null);

        assertTrue(materialPathService.addMaterialPath(1L, "path"));
    }

    @Test
    public void getMaterialPaths_semCourseId_NotInDB() {
        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> materialPathService.getMaterialPaths(1L));
    }

    @Test
    public void getMaterialPaths_semCourseId_InDB() throws NotFoundException {
        SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(1L).build();
        List<String> materialPaths = List.of("path1", "path2", "path3");

        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(semesterCourse));
        Mockito.when(courseMaterialPathRepository.findAllMaterialPathsBySemCourseId(semesterCourse.getSemCourseId())).thenReturn(materialPaths);

        assertEquals(materialPaths, materialPathService.getMaterialPaths(1L));
    }

    @Test
    public void updateMaterialPath_newPath_Null() throws NotFoundException {
        assertFalse(materialPathService.updateMaterialPath(1L, "oldPath", null));
    }

    @Test
    public void updateMaterialPath_semCourseId_NotInDB() {
        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> materialPathService.updateMaterialPath(1L, "oldPath", "newPath"));
    }

    @Test
    public void updateMaterialPath_semCourseId_InDB_materialPathId_NotInDB() {
        SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(1L).build();

        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(semesterCourse));
        Mockito.when(courseMaterialPathRepository.findById(Mockito.any(CourseMaterialPathId.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> materialPathService.updateMaterialPath(1L, "oldPath", "newPath"));
    }

    @Test
    public void updateMaterialPath_semCourseId_materialPathId_InDB() throws NotFoundException {
        SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(1L).build();
        CourseMaterialPathId courseMaterialPathId = CourseMaterialPathId.builder().semCourse(semesterCourse).materialPath("oldPath").build();
        CourseMaterialPath courseMaterialPath = CourseMaterialPath.builder().id(courseMaterialPathId).build();

        Mockito.when(semesterCourseRepository.findById(semesterCourse.getSemCourseId())).thenReturn(Optional.of(semesterCourse));
        Mockito.when(courseMaterialPathRepository.findById(courseMaterialPathId)).thenReturn(Optional.of(courseMaterialPath));
        Mockito.when(courseMaterialPathRepository.save(Mockito.any())).thenReturn(null);

        assertTrue(materialPathService.updateMaterialPath(1L, "oldPath", "newPath"));
    }

    @Test
    public void deleteMaterialPath_semCourseId_NotInDB() {
        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> materialPathService.deleteMaterialPath(1L, "path"));
    }

    @Test
    public void deleteMaterialPath_semCourseId_InDB_materialPathId_NotInDB() {
        SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(1L).build();

        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(semesterCourse));
        Mockito.when(courseMaterialPathRepository.findById(Mockito.any(CourseMaterialPathId.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> materialPathService.deleteMaterialPath(1L, "path"));
    }

    @Test
    public void deleteMaterialPath_semCourseId_materialPathId_InDB() throws NotFoundException {
        SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(1L).build();
        CourseMaterialPathId courseMaterialPathId = CourseMaterialPathId.builder().semCourse(semesterCourse).materialPath("path").build();
        CourseMaterialPath courseMaterialPath = CourseMaterialPath.builder().id(courseMaterialPathId).build();

        Mockito.when(semesterCourseRepository.findById(semesterCourse.getSemCourseId())).thenReturn(Optional.of(semesterCourse));
        Mockito.when(courseMaterialPathRepository.findById(courseMaterialPathId)).thenReturn(Optional.of(courseMaterialPath));
        Mockito.doNothing().when(courseMaterialPathRepository).delete(courseMaterialPath);

        assertTrue(materialPathService.deleteMaterialPath(1L, "path"));
    }

}