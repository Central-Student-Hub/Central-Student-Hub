package com.centralstudenthub.repository;

import com.centralstudenthub.entity.AssignmentMaterialPath;
import com.centralstudenthub.entity.AssignmentMaterialPathId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentMaterialPathRepository
        extends JpaRepository<AssignmentMaterialPath,AssignmentMaterialPathId> {

}


