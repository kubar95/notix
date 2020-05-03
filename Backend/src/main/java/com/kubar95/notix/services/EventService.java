package com.kubar95.notix.services;

import com.kubar95.notix.api.model.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getAllEvents();

    void deleteEventById(Long eventId);
}
