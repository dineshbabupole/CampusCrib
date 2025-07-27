package com.Dinesh.CampusCribe.Reposiories;

import com.Dinesh.CampusCribe.models.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface HostelRepo extends JpaRepository<Hostel,Long> {
    Hostel findByCollege(String clgName);
}
