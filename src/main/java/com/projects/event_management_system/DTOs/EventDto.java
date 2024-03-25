package com.projects.event_management_system.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventDto {
    private String event_name;
    private String city_name;
    private Date date;
    private String weather;
    private double distance_km;
}