package com.projects.event_management_system.controllers;

import com.projects.event_management_system.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/bycityAndDate")
    public String getFormattedWeatherByCityAndDate(@RequestParam String cityName,
                                                   @RequestParam String dateString) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid date format";
        }

        try {
            return weatherService.getFormattedWeatherByCityAndDate(cityName, date);
        } catch (HttpClientErrorException e) {
            return "City not found";
        }
    }

    @GetMapping("/byCoordinate")
    public String getFormattedWeather(@RequestParam double latitude,
                                                   @RequestParam double longitude,@RequestParam String dateString) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid date format";
        }

        try {
            return weatherService.getFormattedWeather(latitude,longitude);
        } catch (HttpClientErrorException e) {
            return "City not found";
        }
    }
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found");
    }
}
