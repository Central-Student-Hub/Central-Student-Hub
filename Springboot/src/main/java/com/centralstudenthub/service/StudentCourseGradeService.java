package com.centralstudenthub.service;

import com.centralstudenthub.Model.Response.CourseResponse;
import com.centralstudenthub.Model.Response.StudentProfileResponse;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGradeId;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.CourseRepository;
import com.centralstudenthub.repository.StudentCourseGradeRepository;
import com.centralstudenthub.repository.StudentProfileRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseGradeService {
    private final CourseRepository courseRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final StudentCourseGradeRepository studentCourseGradeRepository;

    @Autowired
    public StudentCourseGradeService(CourseRepository courseRepository,
                                     StudentProfileRepository studentProfileRepository,
                                     StudentCourseGradeRepository studentCourseGradeRepository) {
        this.courseRepository = courseRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.studentCourseGradeRepository = studentCourseGradeRepository;
    }

    private StudentCourseGrade getStudentCourseGradeEntity(int studentId, int courseId) throws NotFoundException {
        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);
        if (studentProfile.isEmpty())
            throw new NotFoundException("Student profile not found");

        if (course.isEmpty())
            throw new NotFoundException("Course not found");

        StudentCourseGradeId studentCourseGradeId = StudentCourseGradeId.builder().student(studentProfile.get())
                .course(course.get()).build();

        Optional<StudentCourseGrade> studentCourseGrade = studentCourseGradeRepository.findById(studentCourseGradeId);
        if (studentCourseGrade.isEmpty())
            throw new NotFoundException("Student doesn't have a grade for this course");

        return studentCourseGrade.get();
    }

    public boolean addStudentCourseGrade(int courseId, int studentId, double grade) throws
            NotFoundException, AlreadyExistsException {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(studentId);
        if (course.isEmpty())
            throw new NotFoundException("Course not found");

        if (studentProfile.isEmpty())
            throw new NotFoundException("Student profile not found");

        StudentCourseGradeId studentCourseGradeId = StudentCourseGradeId.builder().course(course.get())
                .student(studentProfile.get()).build();

        if (studentCourseGradeRepository.existsById(studentCourseGradeId))
            throw new AlreadyExistsException("Student grade on this course already exists");

        StudentCourseGrade studentCourseGrade = StudentCourseGrade.builder().id(studentCourseGradeId)
                .studentGrade(grade).build();

        studentCourseGradeRepository.save(studentCourseGrade);
        return true;
    }

    public Double getStudentCourseGrade(int studentId, int courseId) throws NotFoundException {
        return getStudentCourseGradeEntity(studentId, courseId).getStudentGrade();
    }

    public List<Pair<CourseResponse, Double>> getStudentGrades(int studentId) throws NotFoundException {
        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(studentId);
        if (studentProfile.isEmpty())
            throw new NotFoundException("Student profile not found");

        List<Object[]> courseIdAndGradeMap = studentCourseGradeRepository.findAllStudentCoursesGrades(studentId);

        List<Pair<CourseResponse, Double>> studentCoursesGrades = new ArrayList<>();
        for (Object[] courseIdAndGrade : courseIdAndGradeMap) {
            Optional<Course> course = courseRepository.findById((Integer) courseIdAndGrade[0]);
            if (course.isEmpty()) continue;
            studentCoursesGrades.add(new Pair<>(course.get().toCourseResponse(), (Double) courseIdAndGrade[1]));
        }
        return studentCoursesGrades;
    }

    public List<Pair<StudentProfileResponse, Double>> getCourseGrades(int courseId) throws NotFoundException {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty())
            throw new NotFoundException("Course not found");

        List<Object[]> studentIdAndGradeMap = studentCourseGradeRepository.findAllCourseStudentsGrades(courseId);

        List<Pair<StudentProfileResponse, Double>> courseStudentsGrades = new ArrayList<>();
        for (Object[] studentIdAndGrade : studentIdAndGradeMap) {
            Optional<StudentProfile> studentProfile = studentProfileRepository.findById((Integer) studentIdAndGrade[0]);
            if (studentProfile.isEmpty()) continue;
            courseStudentsGrades.add(new Pair<>(studentProfile.get().toStudentProfileResponse(),
                    (Double) studentIdAndGrade[1]));
        }
        return courseStudentsGrades;
    }

    public boolean updateCourseGrade(int studentId, int courseId, double newGrade) throws NotFoundException {
        StudentCourseGrade studentCourseGrade = getStudentCourseGradeEntity(studentId, courseId);
        studentCourseGrade.setStudentGrade(newGrade);
        studentCourseGradeRepository.save(studentCourseGrade);
        return true;
    }

    public boolean deleteCourseGrade(int studentId, int courseId) throws NotFoundException {
        studentCourseGradeRepository.delete(getStudentCourseGradeEntity(studentId, courseId));
        return true;
    }
}
