package com.kubar95.notix.services;

import com.kubar95.notix.api.mapper.EventMapper;
import com.kubar95.notix.api.model.EventDTO;
import com.kubar95.notix.domain.Event;
import com.kubar95.notix.domain.User;
import com.kubar95.notix.repository.EventRepository;
import com.kubar95.notix.repository.UserRepository;
import com.kubar95.notix.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SecurityService securityService;
    private final EventMapper eventMapper;

    @Override
    public List<EventDTO> getAllEvents() {
        Long authenticatedUserId = securityService.getUserId();
        User authenticatedUser = userRepository.getById(authenticatedUserId);
        return authenticatedUser.getEvents()
                .stream()
                .map(this::mapToDtoAndSetDateInMilis)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    private EventDTO mapToDtoAndSetDateInMilis (Event event) {
        EventDTO eventDTO = eventMapper.eventToEventDto(event);
        eventDTO.setStartDate(event.getStart().getTime());
        return eventDTO;
    }

}
