package com.centralstudenthub.repository;

import com.centralstudenthub.Model.TeachingStaffContactId;
import com.centralstudenthub.entity.TeachingStaffContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingStaffContactRepository extends JpaRepository<TeachingStaffContact, TeachingStaffContactId> {
}
