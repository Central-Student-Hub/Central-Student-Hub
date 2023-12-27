package com.centralstudenthub.repository;

<<<<<<< HEAD
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location.Location;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location.LocationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, LocationId> {
}
=======
import com.centralstudenthub.entity.sessions.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import com.centralstudenthub.entity.sessions.location.LocationId;

public interface LocationRepository extends JpaRepository<Location, LocationId> {
}
>>>>>>> origin/milestone_3
