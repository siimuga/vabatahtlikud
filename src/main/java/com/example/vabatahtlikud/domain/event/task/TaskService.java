package com.example.vabatahtlikud.domain.event.task;

import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private TaskRepository taskRepository;

    public void getValidTask(TaskRequest request) {
        boolean existsByNameAndEventRegisterIdAndStatus = taskRepository.existsByNameAndEventRegisterIdAndStatus(request.getName(), request.getEventRegisterId());
        ValidationService.validateTaskExists(existsByNameAndEventRegisterIdAndStatus);
    }

    public List<Task> findByStatusTrueAndEventRegisterId(TaskRequest request) {
        return taskRepository.findByStatusTrueAndEventRegisterId(request.getEventRegisterId());
    }

    public List<TaskInfo> addTask(TaskRequest request) {
        getValidTask(request);
        Task task = taskMapper.taskRequestToTask(request);
        taskRepository.save(task);
        List<Task> tasks = findByStatusTrueAndEventRegisterId(request);
        return taskMapper.tasksToTaskInfos(tasks);
    }
}
