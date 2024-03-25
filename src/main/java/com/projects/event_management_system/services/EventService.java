package com.projects.event_management_system.services;

import com.projects.event_management_system.models.Event;
import com.projects.event_management_system.DTOs.EventDto;
import com.projects.event_management_system.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EventService {

    private EventRepository eventRepository;
    private WeatherService weatherService;
    private DistanceCalculatorService distanceCalculatorService;
    @Autowired
    public EventService(EventRepository eventRepository,WeatherService weatherService,DistanceCalculatorService distanceCalculatorService){
        this.eventRepository=eventRepository;
        this.weatherService=weatherService;
        this.distanceCalculatorService=distanceCalculatorService;
    }



    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public List<EventDto> findEvents(double latitude, double longitude, Date date, int page, int pageSize) {
        Date startDate = date;
        Date endDate = new Date(date.getTime() + 14 * 24 * 60 * 60 * 1000); // Add 14 days to the specified date

        List<Event> events = eventRepository.findEventsWithinDateRangeSortedByDateAndDistance(startDate, endDate, latitude, longitude);

        // Paginate the events
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, events.size());

        if (start >= end) {
            return Collections.emptyList();
        }

        List<Event> paginatedEvents = events.subList(start, end);

        return paginatedEvents.stream()
                .map(event -> convertToEventDto(event, latitude, longitude))
                .collect(Collectors.toList());
    }

    private EventDto convertToEventDto(Event event, double latitude, double longitude) {
        EventDto eventDto = new EventDto();
        eventDto.setEvent_name(event.getEvent_name());
        eventDto.setCity_name(event.getCity_name());
        eventDto.setDate(event.getDate());

        String weatherInfo = weatherService.getFormattedWeatherByCityAndDate(event.getCity_name(), event.getDate());
        eventDto.setWeather(weatherInfo);

        double distance = distanceCalculatorService.calculateDistance(latitude, longitude, event.getLatitude(), event.getLongitude());
        eventDto.setDistance_km(distance);

        return eventDto;
    }










}