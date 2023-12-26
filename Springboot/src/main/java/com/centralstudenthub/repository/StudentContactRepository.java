package com.centralstudenthub.repository;


import com.centralstudenthub.Model.Request.ContactModel;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.entity.student_profile.student_contacts.StudentContact;
import com.centralstudenthub.entity.student_profile.student_contacts.StudentContactId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentContactRepository extends JpaRepository<StudentContact, StudentContactId> {
    @Modifying
    @Transactional
    @Query(value = "delete from student_contact where studentId = ?1",nativeQuery = true)
    void deleteAllById_Student(int id);

    @Query(value = "select * from student_contact where studentId = ?1", nativeQuery = true)
    List<StudentContact> findAllByStudentId(int studentId);
}
