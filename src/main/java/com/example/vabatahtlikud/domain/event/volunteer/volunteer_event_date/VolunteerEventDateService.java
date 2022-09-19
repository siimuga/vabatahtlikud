package com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date;

import com.example.vabatahtlikud.domain.event.date.EventDate;
import com.example.vabatahtlikud.domain.event.date.EventDateRepository;
import com.example.vabatahtlikud.domain.event.date.EventDateService;
import com.example.vabatahtlikud.domain.event.volunteer.Volunteer;
import com.example.vabatahtlikud.domain.event.volunteer.VolunteerRepository;
import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerEventDateService {

    @Resource
    private VolunteerEventDateMapper volunteerEventDateMapper;

    @Resource
    private VolunteerEventDateRepository volunteerEventDateRepository;

    @Resource
    private VolunteerRepository volunteerRepository;

    @Resource
    private EventDateRepository eventDateRepository;

    @Resource
    private EventDateService eventDateService;

    public void addDatesToVolunteer(List<VolunteerEventDateInfo> volunteerEventDateInfos) {
        ValidationService.validateDateSelectionExists(volunteerEventDateInfos);
        List<VolunteerEventDate> volunteerEventDates = volunteerEventDateMapper.volunteerEventDateInfosToVolunteerEventDates((volunteerEventDateInfos));
        Integer volunteerId = volunteerEventDateInfos.get(0).getVolunteerId();
        Optional<Volunteer> volunteer = volunteerRepository.findById(volunteerId);
        Integer volunteersSize = volunteer.get().getVolunteersSize();
        for (VolunteerEventDate volunteerEventDate : volunteerEventDates) {
            Optional<EventDate> eventDate = eventDateRepository.findById(volunteerEventDate.getEventDate().getId());
            Integer volunteersRequired = eventDate.get().getVolunteersRequired();
            Integer volunteersAssignedPrev = eventDate.get().getVolunteersAssigned();
            Integer possibleVolunteerAmount = volunteersAssignedPrev + volunteersSize;
            LocalDate date = eventDate.get().getDate();
            ValidationService.validateExceedingAttendance(possibleVolunteerAmount, volunteersRequired, date);
        }
        volunteerEventDateRepository.saveAll(volunteerEventDates);

        List<VolunteerEventDate> volunteerEventDatesList = volunteerEventDateRepository.findByVolunteerId(volunteerId);
        for (VolunteerEventDate volunteerEventDate : volunteerEventDatesList) {
            Optional<EventDate> eventDate = eventDateRepository.findById(volunteerEventDate.getEventDate().getId());
            Integer volunteersAssignedPrev = eventDate.get().getVolunteersAssigned();
            eventDate.get().setVolunteersAssigned(volunteersAssignedPrev + volunteersSize);
            if (volunteersAssignedPrev + volunteersSize >= eventDate.get().getVolunteersRequired()) {
                eventDate.get().setIsActive(false);
            }
        }
        volunteerEventDateRepository.saveAll(volunteerEventDatesList);
    }

    public void deleteParticipation(List<Volunteer> volunteers) {
        for (Volunteer volunteer : volunteers) {
            Integer volunteersSize = volunteer.getVolunteersSize();
            Optional<VolunteerEventDate> volunteerEventDate = volunteerEventDateRepository.findByVolunteer_Id(volunteer.getId());
            Integer eventDateId = volunteerEventDate.get().getId();
            Optional<EventDate> eventDate = eventDateRepository.findById(eventDateId);
            Integer volunteersAssignedPrev = eventDate.get().getVolunteersAssigned();
            if (volunteersAssignedPrev > 0) {
                eventDate.get().setVolunteersAssigned(volunteersAssignedPrev - volunteersSize);
                eventDateRepository.save(eventDate.get());
            }
        }
    }
}
