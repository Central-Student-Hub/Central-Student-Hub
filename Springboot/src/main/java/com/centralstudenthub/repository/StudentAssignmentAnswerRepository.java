package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.student_assignment_answers.StudentAssignmentAnswer;
import com.centralstudenthub.entity.student_profile.student_assignment_answers.StudentAssignmentAnswerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAssignmentAnswerRepository extends JpaRepository<StudentAssignmentAnswer, StudentAssignmentAnswerId> {

}