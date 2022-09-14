package com.example.vabatahtlikud.domain.event.event;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventMapper {

  //  @Mapping(source = "eventRegisterId", target = "eventRegister.id")
    @Mapping(constant = "c", target = "status")
    Event eventRequestToEvent(EventRequest eventRequest);



//    @Mapping(source = "eventRegisterId", target = "eventRegister.id")
//    Event eventUpdateRequestToEvent(EventUpdateRequest request);



//    @InheritInverseConfiguration(name = "eventRequestToEvent")
//    EventRequest eventToEventRequest(Event event);

//    @InheritConfiguration(name = "eventRequestToEvent")
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Event updateEventFromEventRequest(EventRequest eventRequest, @MappingTarget Event event);


    @Mapping(source = "id", target = "eventId")
    //@Mapping()
    EventInfo eventToEventInfo(Event event);


    List<EventInfo> eventsToEventInfos(List<Event> events);
}
