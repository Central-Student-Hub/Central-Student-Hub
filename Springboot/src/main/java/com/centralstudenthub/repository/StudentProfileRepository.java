package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer> {
}