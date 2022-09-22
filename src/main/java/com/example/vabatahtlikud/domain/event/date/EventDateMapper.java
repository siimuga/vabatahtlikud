package com.example.vabatahtlikud.domain.event.date;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventDateMapper {


    EventDate eventDateInfoToEventDate(EventDateInfo eventDateInfo);

    List<EventDate> EventDateInfosToEventDates(List<EventDateInfo> eventDateInfos);


    EventDateInfo eventDateToEventDateInfo(EventDate eventDate);

}
