package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer> {
}
