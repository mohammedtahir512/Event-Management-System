package com.projects.event_management_system.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherInfo {
    private String description;
    private double temp;
}