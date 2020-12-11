package com.example.hydrate.repository;

import com.example.hydrate.model.Water;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterRepository extends JpaRepository<Water, Long> {
}
