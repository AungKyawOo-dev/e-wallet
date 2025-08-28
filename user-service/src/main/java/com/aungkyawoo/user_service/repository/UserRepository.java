package com.aungkyawoo.user_service.repository;

import com.aungkyawoo.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User Repository
 * Author : Aung Kyaw Oo
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Find user by email
    Optional<UserEntity> findByEmail(String email);
}
