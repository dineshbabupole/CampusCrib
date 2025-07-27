package com.Dinesh.CampusCribe.Reposiories;

import com.Dinesh.CampusCribe.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Users findByUserName(String username);

    Optional<Object> findByEmail(String email);
}
