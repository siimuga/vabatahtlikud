package com.example.vabatahtlikud.domain.event.date;

import com.example.vabatahtlikud.domain.event.event.Event;
import com.example.vabatahtlikud.domain.event.event.EventRepository;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventDateService {

    @Resource
    private EventRepository eventRepository;

    @Resource
    private EventDateMapper eventDateMapper;

    public List<EventDateInfo> findAllEventDates(Integer eventId) {

        Optional<Event> event = eventRepository.findById(eventId);
        List<LocalDate> localDates = getDatesBetween((event.get().getStartDate()), event.get().getEndDate());
        // ArrayList<EventDateInfo> infos = new ArrayList<>();
        int totalNrDates = localDates.size();
        ArrayList<EventDateInfo> eventDateInfos = new ArrayList<>(totalNrDates);

        int i = 0;
        for (LocalDate localDate : localDates) {


            eventDateInfos.get(i).setDate(localDate);
            eventDateInfos.get(i).setEventId(eventId);
            eventDateInfos.get(i).setVolunteersRequired(event.get().getVolunteersRequired());
            eventDateInfos.get(i).setVolunteersAssigned(99);
            eventDateInfos.get(i).setDate(localDate);
            eventDateInfos.get(i).setIsActive(true);
            i++;
        }

//        for (EventDateInfo eventDateInfo : eventDateInfos) {
//            eventDateInfo.setEventId(eventId);
//            eventDateInfo.setDate(localDates.get(i));
//            eventDateInfo.setVolunteersRequired(event.get().getVolunteersRequired());
//            eventDateInfo.setVolunteersAssigned(99);
//            eventDateInfo.setIsActive(true);
//            i++;
//        }
        return eventDateInfos;
    }

    public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate.plusDays(1))
                .collect(Collectors.toList());
    }


}
