package com.example.hydrate.service;

import com.example.hydrate.exceptions.WaterNotFoundException;
import com.example.hydrate.model.Review;
import com.example.hydrate.model.Water;
import com.example.hydrate.repository.ReviewRepository;
import com.example.hydrate.repository.WaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class WaterService {

    private WaterRepository waterRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public WaterService(WaterRepository waterRepository, ReviewRepository reviewRepository){
        this.waterRepository=waterRepository;
        this.reviewRepository=reviewRepository;
    }

    public Water getById(Long id) throws WaterNotFoundException {
        Optional<Water> optionalWater=waterRepository.findById(id);
        if(optionalWater.isPresent()){
            return optionalWater.get();
        }else{
            throw new WaterNotFoundException("water of id: "+id.toString()+" was not found");
        }
    }

    public List<Water> listAll(){
        return waterRepository.findAll();
    }

    public Water saveOrUpdate(Water water){
        waterRepository.save(water);
        return water;
    }

    public Water deleteById(Long id) throws WaterNotFoundException {
        Optional<Water> optionalWater=waterRepository.findById(id);
        if(optionalWater.isPresent()){
            Water water=optionalWater.get();
            waterRepository.delete(water);
            return water;
        }else{
            throw new WaterNotFoundException("water of id: "+id.toString()+" was not found, it may have been deleted already");
        }

    }

    public Integer getAverageScore(Water water){
        List<Review> reviewList= reviewRepository.findByWater(water);
        int sum=0;
        int count=0;
        for(Review review:reviewList){
            sum+=review.getScore();
            count++;
        }
        if(count==0){
            return null;
        }else{
            return sum / count;
        }
    }




}
