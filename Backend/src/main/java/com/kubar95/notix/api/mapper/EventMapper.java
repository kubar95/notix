package com.kubar95.notix.api.mapper;

import com.kubar95.notix.api.model.EventDTO;
import com.kubar95.notix.domain.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDTO eventToEventDto (Event event);

}
