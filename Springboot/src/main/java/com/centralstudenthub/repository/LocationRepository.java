package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location.Location;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location.LocationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, LocationId> {
}
