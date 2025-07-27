package com.Dinesh.CampusCribe.Reposiories;

import com.Dinesh.CampusCribe.models.Hostel;
import com.Dinesh.CampusCribe.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepo extends JpaRepository<Reviews,Long> {
}
