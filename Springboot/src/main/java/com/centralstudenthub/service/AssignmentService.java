package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.AssignmentMaterialPathRequest;
import com.centralstudenthub.Model.Request.AssignmentRequest;
import com.centralstudenthub.Model.Request.StudentAssignmentAnswerRequest;
import com.centralstudenthub.Model.StudentCourseResponses.StudentAssignmentAnswerRes;
import com.centralstudenthub.Model.StudentCourseResponses.StudentAssignmentRes;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.Assignment;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.assignment_material_paths.AssignmentMaterialPath;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.assignment_material_paths.AssignmentMaterialPathId;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.student_assignment_answers.StudentAssignmentAnswer;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.student_assignment_answers.StudentAssignmentAnswerId;
import com.centralstudenthub.repository.*;
import com.centralstudenthub.repository.SemesterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private SemesterCourseRepository semCourseRepository;
    @Autowired
    private StudentAssignmentAnswerRepository studentAssignmentAnswerRepository;
    @Autowired
    private AssignmentMaterialPathRepository assignmentMaterialPathRepository;
    @Autowired
     private StudentProfileRepository studentProfileRepository;

    public boolean addAssignment(AssignmentRequest assignmentRequest){

        Optional<SemesterCourse> course = semCourseRepository.findById(assignmentRequest.getSemCourseId());

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

    public List<StudentAssignmentRes> getAllAssignmentByCourseId(Long semCourseId,int studentId) {

        Optional<SemesterCourse> semCourse = semCourseRepository.findById(semCourseId);
        if(semCourse.isEmpty())return null;

        List<Assignment> assignments = semCourse.get().getAssignments();

        return assignments.stream().map( assignment -> {
            return StudentAssignmentRes.builder()
                    .assignmentId(assignment.getAssignmentId())
                    .assignmentName(assignment.getAssignmentName())
                    .assignmentDescription(assignment.getDescription())
                    .assignmentMaterialPaths(
                            assignment.getMaterialPaths().stream().map(
                                assignmentMaterialPath ->{
                                    return assignmentMaterialPath.getAssignmentMaterialPathId().getMaterialPath();
                                }
                            ).toList()
                    )
                    .assignmentDueDate(assignment.getDueDate())
                    .build();
        }).toList();
    }

    public boolean addAssignmentAnswer(StudentAssignmentAnswerRequest ansRequest,int studentId) {

        Optional<Assignment> assignment = assignmentRepository.findById(ansRequest.getAssignmentId());
        Optional<StudentProfile> student = studentProfileRepository.findById(studentId);
        if(assignment.isPresent() && student.isPresent()){
            StudentAssignmentAnswerId studentAssignmentAnswerId = StudentAssignmentAnswerId.builder()
                    .assignment(assignment.get())
                    .student(student.get())
                    .build();
            StudentAssignmentAnswer studentAssignmentAnswer = StudentAssignmentAnswer.builder()
                    .studentAssignmentAnswerId(studentAssignmentAnswerId)
                    .answerPath(ansRequest.getAnswerPath())
                    .grade(0.0)
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
