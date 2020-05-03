package com.kubar95.notix.controllers;

import com.kubar95.notix.api.model.EventDTO;
import com.kubar95.notix.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    private final List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @DeleteMapping("/{id}")
    private void deleteEvent(@PathVariable Long id) {
        eventService.deleteEventById(id);
    }


}
