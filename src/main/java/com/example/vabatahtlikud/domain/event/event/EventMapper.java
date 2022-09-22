package com.example.vabatahtlikud.domain.event.event;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventMapper {

    //  @Mapping(source = "eventRegisterId", target = "eventRegister.id")
    @Mapping(constant = "c", target = "status")
    Event eventRequestToEvent(EventRequest eventRequest);

    @Mapping(source = "id", target = "eventId")
    @Mapping(ignore = true, target = "seqNr")
    @Mapping(ignore = true, target = "pictureData")
    @Mapping(ignore = true, target = "hasPicture")
    EventInfo eventToEventInfo(Event event);


    List<EventInfo> eventsToEventInfos(List<Event> events);


    @Mapping(ignore = true, target = "roleName")
    @Mapping(ignore = true, target = "volunteersAttended")
    @Mapping(ignore = true, target = "seqNr")
    PastEventInfo eventToPastEventInfo(Event event);

    List<PastEventInfo> eventsToPastEventInfos(List<Event> events);


    @Mapping(ignore = true, target = "seqNr")
    @Mapping(ignore = true, target = "roleName")
    @Mapping(ignore = true, target = "volunteersAttended")
    ActiveEventInfo eventToActiveEventInfo(Event event);

    List<ActiveEventInfo> eventsToActiveEventInfos(List<Event> events);
}
