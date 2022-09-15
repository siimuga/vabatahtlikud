package com.example.vabatahtlikud.domain.event.date;

import com.example.vabatahtlikud.domain.event.event.Event;
import com.example.vabatahtlikud.domain.event.event.EventInfo;
import com.example.vabatahtlikud.domain.event.event.EventRepository;
import com.example.vabatahtlikud.domain.event.picture.PictureData;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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

      //  eventDateMapper.eventDatesToEventdateInfos()



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

//        public List<EventInfo> updateEventInfos(List<Event> events) {
//            List<EventInfo> eventInfos = eventMapper.eventsToEventInfos(events);
//            for (EventInfo eventInfo : eventInfos) {
//                Optional<PictureData> picture = pictureDataRepository.findByEventId(eventInfo.getEventId());
//                if (picture.isPresent()) {
//                    String pictureBase64 = new String(picture.get().getData(), StandardCharsets.UTF_8);
//                    eventInfo.setHasPicture(true);
//                    eventInfo.setPictureData(pictureBase64);
//                } else {
//                    eventInfo.setHasPicture(false);
//                }
//                eventInfo.setVolunteersAttended(99);  //SIIN VAJA MUUTA
//            }
//            return eventInfos;
//        }



        return eventDateInfos;
    }

    public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate.plusDays(1))
                .collect(Collectors.toList());
    }


}
