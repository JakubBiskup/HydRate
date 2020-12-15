package com.example.hydrate.controller;

import com.example.hydrate.exceptions.ReviewNotFoundException;
import com.example.hydrate.exceptions.WaterNotFoundException;
import com.example.hydrate.model.Review;
import com.example.hydrate.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/all")
    List<Review> allReviews(){
        return reviewService.listAll();
    }

    @GetMapping("/reviews/water/{water_id}")
    List<Review> singleWaterReviews(@PathVariable Long water_id) throws WaterNotFoundException {
        return reviewService.listSingleWaterReviewsByWaterId(water_id);
    }

    @PostMapping("/reviews")
    Review addReview(@RequestBody Review review) throws WaterNotFoundException {
        return reviewService.saveOrUpdate(review);
    }

    @GetMapping("/reviews/{id}")
    Review singleReview(@PathVariable Long id) throws ReviewNotFoundException {
        return reviewService.getById(id);
    }

    @PutMapping("/reviews/{id}")
    Review editReview(@RequestBody Review editedReview, @PathVariable Long id) throws ReviewNotFoundException, WaterNotFoundException {
        Review reviewToBeEdited=reviewService.getById(id);
        reviewToBeEdited.setScore(editedReview.getScore());
        reviewToBeEdited.setText(editedReview.getText());
        reviewToBeEdited.setWater(editedReview.getWater());
        return reviewService.saveOrUpdate(reviewToBeEdited);
    }

    @DeleteMapping("/reviews/{id}")
    Review deleteReview(@PathVariable Long id) throws ReviewNotFoundException {
        return reviewService.deleteById(id);
    }
}
