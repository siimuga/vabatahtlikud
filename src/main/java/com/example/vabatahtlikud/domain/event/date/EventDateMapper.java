package com.example.vabatahtlikud.domain.event.date;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventDateMapper {

    EventDate eventDateInfoToEventDate(EventDateInfo eventDateInfo);


    EventDateInfo eventDateToEventDateInfo(EventDate eventDate);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventDate updateEventDateFromEventDateInfo(EventDateInfo eventDateInfo, @MappingTarget EventDate eventDate);
}
