package com.centralstudenthub.repository;


import com.centralstudenthub.entity.student_profile.student_contacts.StudentContact;
import com.centralstudenthub.entity.student_profile.student_contacts.StudentContactId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentContactRepository extends JpaRepository<StudentContact, StudentContactId> {
}
