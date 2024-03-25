package com.projects.event_management_system.controllers;

import com.projects.event_management_system.services.DistanceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/distance")
public class DistanceController {

    private final DistanceCalculatorService distanceCalculatorService;

    @Autowired
    public DistanceController(DistanceCalculatorService distanceCalculatorService) {
        this.distanceCalculatorService = distanceCalculatorService;
    }

    @GetMapping("/calculate")
    public double calculateDistance(@RequestParam double userLatitude,
                                        @RequestParam double userLongitude,
                                    @RequestParam double eventLatitude,
                                    @RequestParam double eventLongitude) {
        return distanceCalculatorService.calculateDistance(userLatitude, userLongitude, eventLatitude, eventLongitude);
    }
}
