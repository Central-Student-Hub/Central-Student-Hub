package com.centralstudenthub.repository;


import com.centralstudenthub.entity.student_profile.course.semester_courses.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
}
