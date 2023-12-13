package com.centralstudenthub.repository;

import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContact;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContactId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingStaffContactRepository extends JpaRepository<TeachingStaffContact, TeachingStaffContactId> {
}
