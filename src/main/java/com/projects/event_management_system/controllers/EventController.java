package com.projects.event_management_system.controllers;
import com.projects.event_management_system.DTOs.EventDto;
import com.projects.event_management_system.models.Event;
import com.projects.event_management_system.services.EventService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/events")
    public class EventController {
        @Autowired
        private EventService eventService;
    
        @PostMapping("/add")
        public ResponseEntity<String> createEvent(@RequestBody Event event) {
            eventService.addEvent(event);
            return ResponseEntity.ok("Event created successfully");
        }
    
    
        @GetMapping("/find")
        public ResponseEntity<List<EventDto>> findEvents(@RequestParam double latitude, @RequestParam double longitude, @RequestParam(name = "searchDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
            List<EventDto> events = eventService.findEvents(latitude, longitude, date, page, pageSize);
            return ResponseEntity.ok(events);
        }
    
    }

