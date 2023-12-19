package com.centralstudenthub.repository;

import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContact;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContactId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TeachingStaffContactRepository extends JpaRepository<TeachingStaffContact, TeachingStaffContactId> {
    @Modifying
    @Transactional
    @Query(value = "delete from teaching_staff_contact where teacherId = ?1",nativeQuery = true)
    void deleteAllById_Teacher(int id);
}
