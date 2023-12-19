package com.centralstudenthub.repository;


import com.centralstudenthub.Model.Request.ContactModel;
import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OfficeHourRepository extends JpaRepository<OfficeHour, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from office_hour where teacherId = ?1",nativeQuery = true)
    void deleteAllByTeacher(int id);

    @Query(value = "select * from office_hour where teacherId = ?1",nativeQuery = true)
    List<OfficeHour> getAllById_Teacher(int id);

}
