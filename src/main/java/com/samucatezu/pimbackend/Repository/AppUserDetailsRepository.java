package com.samucatezu.pimbackend.Repository;

import com.samucatezu.pimbackend.Model.AppUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserDetailsRepository
        extends JpaRepository<AppUserDetails, Long> {

    Optional<AppUserDetails> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUserDetails a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

}