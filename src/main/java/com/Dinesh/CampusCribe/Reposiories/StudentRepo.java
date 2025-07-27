package com.Dinesh.CampusCribe.Reposiories;

import com.Dinesh.CampusCribe.models.Hostel;
import com.Dinesh.CampusCribe.models.Owner;
import com.Dinesh.CampusCribe.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
   Student findByMail(String mail);
}
