package com.example.vabatahtlikud.domain.event.event;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "eventRegisterId", target = "eventRegister.id")
    @Mapping(constant = "c", target = "status")
    Event eventRequestToEvent(EventRequest eventRequest);



    @Mapping(source = "eventRegisterId", target = "eventRegister.id")
    Event eventUpdateRequestToEvent(EventUpdateRequest request);



    @InheritInverseConfiguration(name = "eventRequestToEvent")
    EventRequest eventToEventRequest(Event event);

    @InheritConfiguration(name = "eventRequestToEvent")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event updateEventFromEventRequest(EventRequest eventRequest, @MappingTarget Event event);
}
