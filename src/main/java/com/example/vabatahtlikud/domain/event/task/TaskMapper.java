package com.example.vabatahtlikud.domain.event.task;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TaskMapper {

    @Mapping(constant = "true", target = "status")
    Task taskRequestToTask(TaskRequest taskRequest);

    TaskRequest taskToTaskRequest(Task task);


    TaskInfo taskToTaskInfo(Task task);

    List<TaskInfo> tasksToTaskInfos(List<Task> tasks);

    @Mapping(source = "event.id", target = "eventId")
    TaskDateInfo taskToTaskDateInfo(Task task);

    List<TaskDateInfo> tasksToTaskDateInfos(List<Task> tasks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task updateTaskFromTaskRequest(TaskRequest taskRequest, @MappingTarget Task task);
}
