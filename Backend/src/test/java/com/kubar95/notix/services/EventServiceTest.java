package com.kubar95.notix.services;

import com.kubar95.notix.api.mapper.EventMapper;
import com.kubar95.notix.api.model.EventDTO;
import com.kubar95.notix.domain.Event;
import com.kubar95.notix.domain.User;
import com.kubar95.notix.repository.EventRepository;
import com.kubar95.notix.repository.UserRepository;
import com.kubar95.notix.security.services.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EventServiceTest {

    EventService eventService;

    @Mock
    UserRepository userRepository;

    @Mock
    EventRepository eventRepository;

    @Mock
    SecurityService securityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        eventService = new EventServiceImpl(userRepository,eventRepository, securityService, EventMapper.INSTANCE);
    }

    @Test
    void getAllEvents() {
        //given
        Event event1 = new Event();
        event1.setId(1L);
        Event event2 = new Event();
        event2.setId(2L);

        List<Event> events = Arrays.asList(event1,event2);

        User user = new User();
        user.setEvents(events);

        when(userRepository.getById(anyLong())).thenReturn(user);

        //when
        List<EventDTO> eventDTOS = eventService.getAllEvents();

        assertEquals(2, eventDTOS.size());
        verify(userRepository, times(1)).getById(anyLong());

    }
}