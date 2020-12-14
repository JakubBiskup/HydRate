package com.example.hydrate.repository;

import com.example.hydrate.model.Review;
import com.example.hydrate.model.Water;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByWater(Water water);
}
