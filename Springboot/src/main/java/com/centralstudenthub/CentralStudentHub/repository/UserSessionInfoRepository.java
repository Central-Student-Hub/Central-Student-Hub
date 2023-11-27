package com.centralstudenthub.CentralStudentHub.repository;

import com.centralstudenthub.CentralStudentHub.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserSessionInfoRepository extends JpaRepository<UserAccount,Long> {

    Optional<UserAccount> findByEmail(String email);
    Optional<UserAccount> findBySsn(String email);
}
