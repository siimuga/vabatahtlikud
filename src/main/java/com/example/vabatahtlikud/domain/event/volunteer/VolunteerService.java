package com.example.vabatahtlikud.domain.event.volunteer;

import com.example.vabatahtlikud.domain.event.date.EventDateService;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDateInfo;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDateService;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_task.VolunteerTaskInfo;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_task.VolunteerTaskService;
import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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


    public void addRegistration(VolunteerRequest request) {
        Volunteer volunteer = volunteerMapper.volunteerRequestToVolunteer(request);
        if (volunteerRepository.existsByIds(request.getUserId(), request.getEventId())) {
            ValidationService.validateRegistrationExists();
        }
        volunteerRepository.save(volunteer);
    }

    public void addTasksToVolunteer(List<VolunteerTaskInfo> volunteerTaskInfos) {
        volunteerTaskService.addTasksToVolunteer(volunteerTaskInfos);
    }

    public void addDatesToVolunteer(List<VolunteerEventDateInfo> volunteerEventDateInfos) {
        volunteerEventDateService.addDatesToVolunteer(volunteerEventDateInfos);
    }


}
