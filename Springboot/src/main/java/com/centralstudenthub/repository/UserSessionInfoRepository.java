package com.centralstudenthub.repository;

import com.centralstudenthub.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionInfoRepository extends JpaRepository<UserAccount,Long> {

    Optional<UserAccount> findByEmail(String email);
    Optional<UserAccount> findBySsn(String email);
    Optional<UserAccount> findByGmail(String gmail);
}
