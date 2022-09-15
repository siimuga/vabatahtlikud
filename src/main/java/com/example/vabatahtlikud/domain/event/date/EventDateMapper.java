package com.example.vabatahtlikud.domain.event.date;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventDateMapper {
    @Mapping(source = "eventId", target = "event.id")
    EventDate eventDateInfoToEventDate(EventDateInfo eventDateInfo);

    @Mapping(source = "event.id", target = "eventId")
    EventDateInfo eventDateToEventDateInfo(EventDate eventDate);

    @Mapping(source = "eventId", target = "event.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventDate updateEventDateFromEventDateInfo(EventDateInfo eventDateInfo, @MappingTarget EventDate eventDate);
}
