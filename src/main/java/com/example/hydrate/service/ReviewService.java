package com.example.hydrate.service;

import com.example.hydrate.exceptions.ReviewNotFoundException;
import com.example.hydrate.exceptions.WaterNotFoundException;
import com.example.hydrate.model.Review;
import com.example.hydrate.model.Water;
import com.example.hydrate.repository.ReviewRepository;
import com.example.hydrate.repository.WaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private WaterRepository waterRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, WaterRepository waterRepository) {
        this.reviewRepository = reviewRepository;
        this.waterRepository= waterRepository;
    }

    public Review getById(Long id) throws ReviewNotFoundException {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if(optionalReview.isPresent()){
            return optionalReview.get();
        }else {
            throw new ReviewNotFoundException("Review of id: "+id.toString()+" was not found");
        }
    }

    public List<Review> listAll(){
        return reviewRepository.findAll();
    }

    public List<Review> listSingleWaterReviewsByWaterId(Long id) throws WaterNotFoundException {
        Optional<Water> optWater = waterRepository.findById(id);
        if(optWater.isPresent()){
            return listGivenWaterReviews(optWater.get());
        }else{
            throw new WaterNotFoundException("water of id: "+id.toString()+" was not found");
        }
    }

    public List<Review> listGivenWaterReviews(Water water){
        return reviewRepository.findByWater(water);
    }

    public Review saveOrUpdate(Review review) throws WaterNotFoundException {
        if(waterRepository.findById(review.getWater().getId()).isPresent()) {
            reviewRepository.save(review);
            return review;
        }else{
            throw new WaterNotFoundException("Water that you are reviewing was not found");
        }
    }

    public Review deleteById(Long id) throws ReviewNotFoundException {
        Optional<Review> optionalReview=reviewRepository.findById(id);
        if(optionalReview.isPresent()){
            Review deletedReview=optionalReview.get();
            reviewRepository.delete(deletedReview);
            return deletedReview;
        }else{
            throw new ReviewNotFoundException("review of id: "+id.toString()+" was not found, it may have been deleted already");
        }
    }



}
