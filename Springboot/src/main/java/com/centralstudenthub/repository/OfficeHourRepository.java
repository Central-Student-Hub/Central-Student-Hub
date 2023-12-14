package com.centralstudenthub.repository;


import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficeHourRepository extends JpaRepository<OfficeHour, Integer> {}
