package com.centralstudenthub.service;

import com.centralstudenthub.Model.Response.student_profile.course.StudentCourseGradeResponse;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGradeId;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.CourseRepository;
import com.centralstudenthub.repository.StudentCourseGradeRepository;
import com.centralstudenthub.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private StudentCourseGrade getStudentCourseGradeEntity(Integer studentId, Integer courseId) throws NotFoundException {
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

    public boolean addStudentCourseGrade(Integer courseId, Integer studentId, Double grade) throws
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

    public Double getStudentCourseGrade(Integer studentId, Integer courseId) throws NotFoundException {
        return getStudentCourseGradeEntity(studentId, courseId).getStudentGrade();
    }

    public List<StudentCourseGradeResponse> getStudentGrades(Integer studentId) throws NotFoundException {
        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(studentId);
        if (studentProfile.isEmpty())
            throw new NotFoundException("Student profile not found");

        return studentCourseGradeRepository.findAllStudentCoursesGradesByStudentId(studentId).stream().map(
                StudentCourseGrade::toResponse).toList();
    }
//
//    public List<Pair<StudentProfileResponse, Double>> getCourseGrades(Integer courseId) throws NotFoundException {
//        Optional<Course> course = courseRepository.findById(courseId);
//        if (course.isEmpty())
//            throw new NotFoundException("Course not found");
//
//        List<Object[]> studentIdAndGradeMap = studentCourseGradeRepository.findAllCourseStudentsGradesByCourseId(courseId);
//
//        List<Pair<StudentProfileResponse, Double>> courseStudentsGrades = new ArrayList<>();
//        for (Object[] studentIdAndGrade : studentIdAndGradeMap) {
//            Optional<StudentProfile> studentProfile = studentProfileRepository.findById((Integer) studentIdAndGrade[0]);
//            if (studentProfile.isEmpty()) continue;
//            courseStudentsGrades.add(new Pair<>(studentProfile.get().toReqRes(),
//                    (Double) studentIdAndGrade[1]));
//        }
//        return courseStudentsGrades;
//    }

    public boolean updateCourseGrade(Integer studentId, Integer courseId, Double newGrade) throws NotFoundException {
        StudentCourseGrade studentCourseGrade = getStudentCourseGradeEntity(studentId, courseId);
        if (newGrade != null)
            studentCourseGrade.setStudentGrade(newGrade);
        studentCourseGradeRepository.save(studentCourseGrade);
        return true;
    }

    public boolean deleteCourseGrade(Integer studentId, Integer courseId) throws NotFoundException {
        studentCourseGradeRepository.delete(getStudentCourseGradeEntity(studentId, courseId));
        return true;
    }

    public double calculateGPA(Integer studentId) {
        var courseGrades = studentCourseGradeRepository.findAllStudentCoursesGradesByStudentId(studentId);
        double GPA = 0.0;
        int totalHours = 0;
        for (var courseGrade : courseGrades) {
            double grade = courseGrade.getStudentGrade();
            Integer creditHours = courseGrade.getId().getCourse().getCreditHours();
            GPA += grade * creditHours;
            totalHours += creditHours;
        }
        GPA /= totalHours;
        return GPA;
    }
}
