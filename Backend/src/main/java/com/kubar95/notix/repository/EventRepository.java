package com.kubar95.notix.repository;

import com.kubar95.notix.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Iterable<Event> findAllByOwnerIdOrderByStart (Long ownerId);
}
