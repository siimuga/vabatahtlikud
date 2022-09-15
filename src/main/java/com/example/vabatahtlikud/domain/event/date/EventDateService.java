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

    public List<EventDateInfo> findAllEventDateInfos(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        List<LocalDate> localDates = getDatesBetween((event.get().getStartDate()), event.get().getEndDate());
        ArrayList<EventDateInfo> infos = new ArrayList<>();
        for (LocalDate localDate : localDates) {
            infos.add(new EventDateInfo(localDate, event.get().getVolunteersRequired(), 99, true));
        }
        return infos;
    }

    public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate.plusDays(1))
                .collect(Collectors.toList());
    }
}