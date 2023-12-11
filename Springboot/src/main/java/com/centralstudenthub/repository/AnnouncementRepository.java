package com.centralstudenthub.repository;

import com.centralstudenthub.entity.Announcement;
import com.centralstudenthub.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
}
