package com.rowland.identity.repository;

import com.rowland.identity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    // Spring Data JPA will automatically write the SQL for this method!
    Optional<UserEntity> findByUsername(String username);
}