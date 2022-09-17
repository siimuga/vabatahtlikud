package com.example.vabatahtlikud.domain.event.volunteer;

import com.example.vabatahtlikud.domain.event.event.Event;
import com.example.vabatahtlikud.domain.event.event.EventService;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDateInfo;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDateService;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_task.VolunteerTaskInfo;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_task.VolunteerTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class VolunteerService {
    @Resource
    private VolunteerMapper volunteerMapper;

    @Resource
    private VolunteerEventDateService volunteerEventDateService;

    @Resource
    private VolunteerRepository volunteerRepository;

    @Resource
    private VolunteerTaskService volunteerTaskService;

    @Resource
    private EventService eventService;


    public void addRegistration(VolunteerRequest request) {
        Volunteer volunteer = volunteerMapper.volunteerRequestToVolunteer(request);
        volunteerRepository.save(volunteer);
    }

    public void addTasksToVolunteer(List<VolunteerTaskInfo> volunteerTaskInfos) {
        volunteerTaskService.addTasksToVolunteer(volunteerTaskInfos);
    }

    public void addDatesToVolunteer(List<VolunteerEventDateInfo> volunteerEventDateInfos) {
        volunteerEventDateService.addDatesToVolunteer(volunteerEventDateInfos);
    }

    public List<Event> findAllActiveEventsByUser(Integer userId) {
        ArrayList<Event> events = new ArrayList<>();
        List<Volunteer> volunteers = volunteerRepository.findByUser(userId);
        for (Volunteer volunteer : volunteers) {
            Integer volunteeruUserId = volunteer.getUser().getId();
           Event event=  eventService.findEventByUser(volunteeruUserId);
            events.add(event);
        }
        return events;
    }
}
