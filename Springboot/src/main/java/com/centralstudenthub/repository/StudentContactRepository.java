package com.centralstudenthub.repository;

import com.centralstudenthub.entity.StudentContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentContactRepository extends JpaRepository<StudentContact, StudentContactId> {
}
