package com.example.vabatahtlikud.domain.event.volunteer;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VolunteerTaskMapper {

    VolunteerTask volunteerTaskInfoToVolunteerTask(VolunteerTaskInfo volunteerTaskInfo);

    List<VolunteerTask> volunteerTaskInfosToVolunteerTasks(List<VolunteerTaskInfo> volunteerTaskInfos);

    @InheritInverseConfiguration(name = "volunteerTaskInfoToVolunteerTask")
    VolunteerTaskInfo volunteerTaskToVolunteerTaskInfo(VolunteerTask volunteerTask);

    @InheritConfiguration(name = "volunteerTaskInfoToVolunteerTask")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VolunteerTask updateVolunteerTaskFromVolunteerTaskInfo(VolunteerTaskInfo volunteerTaskInfo, @MappingTarget VolunteerTask volunteerTask);


}
