package com.Dinesh.CampusCribe.controllers;

import com.Dinesh.CampusCribe.Services.ReviewService;
import com.Dinesh.CampusCribe.models.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*")
public class ReviewsController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping("/add")
    public void addReview(@RequestBody Reviews reviews){
        reviewService.addReview(reviews);
    }
}
