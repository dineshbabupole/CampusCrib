package com.Dinesh.CampusCribe.Services;


import com.Dinesh.CampusCribe.Exceptions.HostelNotFoundException;
import com.Dinesh.CampusCribe.Exceptions.UserNotFoundException;
import com.Dinesh.CampusCribe.Reposiories.HostelRepo;
import com.Dinesh.CampusCribe.Reposiories.ReviewsRepo;
import com.Dinesh.CampusCribe.Reposiories.StudentRepo;
import com.Dinesh.CampusCribe.models.Hostel;
import com.Dinesh.CampusCribe.models.Reviews;
import com.Dinesh.CampusCribe.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewsRepo reviewsRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private HostelRepo hostelRepo;


    public void addReview(Reviews reviews){
        Long studentId=reviews.getStudent().getId();
        Long hostelId=reviews.getHostel().getId();
        Student student=studentRepo.findById(studentId).orElseThrow(()->new UserNotFoundException("student not found"));
        Hostel hostel=hostelRepo.findById(hostelId).orElseThrow(()->new HostelNotFoundException("hostel not found"));
        reviews.setHostel(hostel);
        reviews.setStudent(student);
        student.getReviews().add(reviews);
        hostel.getReviews().add(reviews);
        reviewsRepo.save(reviews);
    }


}
