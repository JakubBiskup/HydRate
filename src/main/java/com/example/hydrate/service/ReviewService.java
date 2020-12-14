package com.example.hydrate.service;

import com.example.hydrate.exceptions.ReviewNotFoundException;
import com.example.hydrate.model.Review;
import com.example.hydrate.model.Water;
import com.example.hydrate.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
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

    public Review saveOrUpdate(Review review){
        reviewRepository.save(review);
        return review;
    }

    public void deleteById(Long id){
        reviewRepository.deleteById(id);
    }



}
