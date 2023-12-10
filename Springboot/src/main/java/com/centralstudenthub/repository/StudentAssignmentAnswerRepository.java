package com.centralstudenthub.repository;

import com.centralstudenthub.entity.StudentAssignmentAnswer;
import com.centralstudenthub.entity.StudentAssignmentAnswerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentAssignmentAnswerRepository extends JpaRepository<StudentAssignmentAnswer, StudentAssignmentAnswerId> {

}