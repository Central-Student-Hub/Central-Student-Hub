package com.centralstudenthub.repository;

import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachingStaffProfileRepository extends JpaRepository<TeachingStaffProfile, Integer> {
}
