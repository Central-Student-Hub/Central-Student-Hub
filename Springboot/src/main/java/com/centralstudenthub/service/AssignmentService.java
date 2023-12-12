package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.AssignmentMaterialPathRequest;
import com.centralstudenthub.Model.Request.AssignmentRequest;
import com.centralstudenthub.Model.Request.StudentAssignmentAnswerRequest;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.Assignment;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.assignment_material_paths.AssignmentMaterialPath;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.assignment_material_paths.AssignmentMaterialPathId;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.repository.*;
import com.centralstudenthub.repository.SemesterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private SemesterCourseRepository courseRepository;
    @Autowired
    private StudentAssignmentAnswerRepository studentAssignmentAnswerRepository;

    @Autowired
    private AssignmentMaterialPathRepository assignmentMaterialPathRepository;


    //todo @Autowired
     //todo private StudentProfileRepository studentProfileRepository



    public boolean addAssignment(AssignmentRequest assignmentRequest){

        Optional<SemesterCourse> course = courseRepository.findById(assignmentRequest.getSemCourseId());

        if(course.isPresent()){
            Assignment assignment = Assignment.builder()
                    .assignmentName(assignmentRequest.getAssignmentName())
                    .description(assignmentRequest.getDescription())
                    .dueDate(assignmentRequest.getDueDate())
                    .semCourse(course.get())
                    .build();

            assignmentRepository.save(assignment);
            return true;
        }
        else{
            return false;
        }
    }

    public Assignment getAssignment(Long assignmentId) {

        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);

        if(assignment.isPresent()) return assignment.get();
        else return null;
    }

    public boolean addAssignmentAnswer(StudentAssignmentAnswerRequest ansRequest) {

        // todo  dont forget to change the primarykey
/*
        Optional<Assignment> assignment = assignmentRepository.findById(ansRequest.getAssignmentId());
        Optional<StudentProfile> student = studentProfileRepository.findById(ansRequest.getStudentProfileId());
        if(assignment.isPresent() && student.isPresent()){
            StudentAssignmentAnswer studentAssignmentAnswer = StudentAssignmentAnswer.builder()
                    .answerPath(ansRequest.getAnswerPath())
                    .assignment(assignment.get())
                    .student(student.get())
                    .grade(ansRequest.getGrade())
                    .build();
            studentAssignmentAnswerRepository.save(studentAssignmentAnswer);
            return true;
        }
        else{
            return false;
        }

 */

        return true;

    }

    public boolean addAssignmentMaterialPath(AssignmentMaterialPathRequest materialPassRequest) {
        Optional<Assignment> assignment = assignmentRepository.findById(materialPassRequest.getAssignmentId());

        if(assignment.isPresent()){
            AssignmentMaterialPathId assignmentMaterialPathId = AssignmentMaterialPathId.builder()
                    .materialPath(materialPassRequest.getMaterialPath())
                    .assignment(assignment.get())
                    .build();
            AssignmentMaterialPath assignmentMaterialPath = AssignmentMaterialPath.builder()
                    .assignmentMaterialPathId(assignmentMaterialPathId)
                    .build();

            assignmentMaterialPathRepository.save(assignmentMaterialPath);
            return true;
        }
        else{
            return false;
        }
    }
}
