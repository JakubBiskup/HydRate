package com.example.hydrate.controller;

import com.example.hydrate.exceptions.WaterNotFoundException;
import com.example.hydrate.model.Water;
import com.example.hydrate.service.WaterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class WaterController {

    private final WaterService waterService;


    public WaterController(WaterService waterService) {
        this.waterService = waterService;
    }

    @GetMapping("/water/all")
    public List<Water> allWater(){
        return waterService.listAll();
    }

    @PostMapping("/water")
    public Water addWater(@RequestBody Water water){
        return waterService.saveOrUpdate(water);
    }

    @PutMapping("/water/{id}")
    public Water editWater(@RequestBody Water editedWater, @PathVariable Long id) throws WaterNotFoundException {
        Water waterToBeEdited=waterService.getById(id);
        waterToBeEdited.setCompany(editedWater.getCompany());
        waterToBeEdited.setDescription(editedWater.getDescription());
        waterToBeEdited.setMinerals(editedWater.getMinerals());
        waterToBeEdited.setName(editedWater.getName());
        waterToBeEdited.setSource(editedWater.getSource());
        return waterService.saveOrUpdate(waterToBeEdited);
    }

    @GetMapping("/water/{id}")
    public Water singleWater(@PathVariable Long id) throws WaterNotFoundException{
        return waterService.getById(id);

    }

    @DeleteMapping("/water/{id}")
    public Water deleteWater(@PathVariable Long id) throws WaterNotFoundException{
        return waterService.deleteById(id);
    }

    @GetMapping("/water/{id}/average_score")
    public Integer getWaterAvgScore(@PathVariable Long id) throws WaterNotFoundException {
        return waterService.getAverageScore(waterService.getById(id));
    }

}
