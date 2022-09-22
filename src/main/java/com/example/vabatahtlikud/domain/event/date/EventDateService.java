package com.example.vabatahtlikud.domain.event.date;

import com.example.vabatahtlikud.domain.event.event.Event;
import com.example.vabatahtlikud.domain.event.event.EventRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventDateService {

    @Resource
    private EventRepository eventRepository;

    @Resource
    private EventDateRepository eventDateRepository;

    @Resource
    private EventDateMapper eventDateMapper;


    public List<EventDateInfo> findAllEventDateInfos(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        List<LocalDate> localDates = getDatesBetween((event.get().getStartDate()), event.get().getEndDate());
        List<EventDate> eventDates = eventDateRepository.findByEventId(eventId);
        ArrayList<EventDateInfo> infos = new ArrayList<>();
        for (LocalDate localDate : localDates) {
            Integer eventDateId = eventDates.get(localDates.indexOf(localDate)).getId();
            Integer volunteersAssigned = eventDates.get(localDates.indexOf(localDate)).getVolunteersAssigned();
            Integer volunteersRequired = eventDates.get(localDates.indexOf(localDate)).getVolunteersRequired();
            LocalDate date = eventDates.get(localDates.indexOf(localDate)).getDate();
            Boolean isActive = eventDates.get(localDates.indexOf(localDate)).getIsActive();
            if (isActive) {
                infos.add(new EventDateInfo(eventDateId, date, volunteersRequired, volunteersAssigned, true));
            }
        }
        return infos;
    }

    public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate.plusDays(1))
                .collect(Collectors.toList());
    }

    public void addDateInfos(Integer eventId) {
        List<EventDateInfo> eventDateInfos = initializeDateInfos(eventId);
        List<EventDate> eventDates = eventDateMapper.EventDateInfosToEventDates(eventDateInfos);
        for (EventDate eventDate : eventDates) {
            Optional<Event> event = eventRepository.findById(eventId);
            eventDate.setEvent(event.get());
        }
        eventDateRepository.saveAll(eventDates);
    }

    private List<EventDateInfo> initializeDateInfos(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        List<LocalDate> localDates = getDatesBetween((event.get().getStartDate()), event.get().getEndDate());
        ArrayList<EventDateInfo> infos = new ArrayList<>();
        for (LocalDate localDate : localDates) {
            infos.add(new EventDateInfo(localDate, event.get().getVolunteersRequired(), 0, true));
        }
        return infos;
    }
}