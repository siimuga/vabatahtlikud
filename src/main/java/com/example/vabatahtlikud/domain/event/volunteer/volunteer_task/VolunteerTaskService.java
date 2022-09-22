package com.example.vabatahtlikud.domain.event.volunteer.volunteer_task;

import com.example.vabatahtlikud.domain.event.volunteer.VolunteerRequestDatas;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDate;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDateInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class VolunteerTaskService {

    @Resource
    private VolunteerTaskMapper volunteerTaskMapper;

    @Resource
    private VolunteerTaskRepository volunteerTaskRepository;


    public void addTasksToVolunteer(List<VolunteerTaskInfo> volunteerTaskInfos) {
        List<VolunteerTask> volunteerTaskResult = volunteerTaskMapper.volunteerTaskInfosToVolunteerTasks(volunteerTaskInfos);
        volunteerTaskRepository.saveAll(volunteerTaskResult);
    }

    public void addRegistrationDatas(VolunteerRequestDatas request) {
        List<Integer> volunteerTaskInfoIds = request.getVolunteerTaskInfos();
        Integer volunteerId = request.getVolunteerId();
        List<VolunteerTaskInfo> volunteerTaskInfos = new ArrayList<>();
        for (Integer volunteerTaskInfo : volunteerTaskInfoIds) {
            volunteerTaskInfos.add(new VolunteerTaskInfo(volunteerId, volunteerTaskInfo));
        }
        List<VolunteerTask> volunteerTasks =volunteerTaskMapper.volunteerTaskInfosToVolunteerTasks(volunteerTaskInfos);
        volunteerTaskRepository.saveAll(volunteerTasks);
    }
}
