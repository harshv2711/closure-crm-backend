package com.closurecrm.api.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.closurecrm.api.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.providerId = :providerId")
    Optional<User> findByProviderId(@Param("providerId") String providerId);
}