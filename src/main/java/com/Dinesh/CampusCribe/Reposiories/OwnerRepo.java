package com.Dinesh.CampusCribe.Reposiories;

import com.Dinesh.CampusCribe.models.Hostel;
import com.Dinesh.CampusCribe.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends JpaRepository<Owner,Long> {
    Owner findByMail(String mail);
}
