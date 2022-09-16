package com.example.vabatahtlikud.domain.event.volunteer;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
