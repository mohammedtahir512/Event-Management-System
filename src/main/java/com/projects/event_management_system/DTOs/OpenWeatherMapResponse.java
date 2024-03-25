package com.projects.event_management_system.DTOs;

import com.projects.event_management_system.models.WeatherInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenWeatherMapResponse {
    private WeatherInfo[] weather;

}