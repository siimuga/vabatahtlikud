package com.example.vabatahtlikud.domain.event.volunteer.volunteer_task;

import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDateInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VolunteerTaskMapper {

    @Mapping(source = "volunteerId", target = "volunteer.id")
    @Mapping(source = "taskId", target = "task.id")
    VolunteerTask volunteerTaskInfoToVolunteerTask(VolunteerTaskInfo volunteerTaskInfo);

    List<VolunteerTask> volunteerTaskInfosToVolunteerTasks(List<VolunteerTaskInfo> volunteerTaskInfos);

    @InheritInverseConfiguration(name = "volunteerTaskInfoToVolunteerTask")
    VolunteerTaskInfo volunteerTaskToVolunteerTaskInfo(VolunteerTask volunteerTask);

}
