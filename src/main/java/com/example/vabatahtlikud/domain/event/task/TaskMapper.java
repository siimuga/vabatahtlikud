package com.example.vabatahtlikud.domain.event.task;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "eventRegisterId", target = "eventRegister.id")
    Task taskRequestToTask(TaskRequest taskRequest);

    TaskRequest taskToTaskRequest(Task task);


    TaskInfo taskToTaskInfo(Task task);

    List<TaskInfo> tasksToTaskInfos(List<Task> tasks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task updateTaskFromTaskRequest(TaskRequest taskRequest, @MappingTarget Task task);
}
