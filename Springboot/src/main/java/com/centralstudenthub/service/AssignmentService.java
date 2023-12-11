package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.AssignmentMaterialPathRequest;
import com.centralstudenthub.Model.Request.AssignmentRequest;
import com.centralstudenthub.Model.Request.StudentAssignmentAnswerRequest;
import com.centralstudenthub.entity.*;
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
    @Autowired
     private StudentProfileRepository studentProfileRepository;


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

        return assignment.orElse(null);
    }

    public boolean addAssignmentAnswer(StudentAssignmentAnswerRequest ansRequest) {

        Optional<Assignment> assignment = assignmentRepository.findById(ansRequest.getAssignmentId());
        Optional<StudentProfile> student = studentProfileRepository.findById(ansRequest.getStudentProfileId());
        if(assignment.isPresent() && student.isPresent()){
            StudentAssignmentAnswerId studentAssignmentAnswerId = StudentAssignmentAnswerId.builder()
                    .assignment(assignment.get())
                    .student(student.get())
                    .build();
            StudentAssignmentAnswer studentAssignmentAnswer = StudentAssignmentAnswer.builder()
                    .studentAssignmentAnswerId(studentAssignmentAnswerId)
                    .answerPath(ansRequest.getAnswerPath())
                    .grade(ansRequest.getGrade())
                    .build();
            studentAssignmentAnswerRepository.save(studentAssignmentAnswer);
            return true;
        }
        else{
            return false;
        }
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
