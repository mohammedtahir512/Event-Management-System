package com.projects.event_management_system.services;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Date;

@Service
public class WeatherService {

    private final String baseUrl = "https://api.openweathermap.org/data/2.5/weather";
    private final String apiKey = "a19a10c1e171287aad7e1944f8b8cf39";

    public String getFormattedWeather(double latitude, double longitude) {
        String url = baseUrl + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;
        return fetchWeather(url);
    }

    public String getFormattedWeatherByCityAndDate(String cityName, Date date) {
        long unixTimestamp = date.getTime() / 1000; // Convert Date to Unix timestamp
        String url = baseUrl + "?q=" + cityName + "&dt=" + unixTimestamp + "&appid=" + apiKey;
        return fetchWeather(url);
    }

    private String fetchWeather(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseBody);
                JsonNode mainNode = rootNode.get("main");
                double temperature = mainNode.get("temp").asDouble() - 273.15; // Convert temperature from Kelvin to Celsius
                String weatherDescription = rootNode.get("weather").get(0).get("description").asText();
                return capitalize(weatherDescription) + " " + (int) temperature + "C";
            } catch (IOException e) {
                e.printStackTrace();
                return "Error reading weather data";
            }
        } else {
            return "Failed to fetch weather data";
        }
    }


    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}





