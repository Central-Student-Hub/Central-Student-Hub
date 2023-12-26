package com.centralstudenthub.repository;

import com.centralstudenthub.entity.sessions.location.Location;
import com.centralstudenthub.entity.sessions.location.LocationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, LocationId> {
}
