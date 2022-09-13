package com.example.vabatahtlikud.domain.event.task;

import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private TaskRepository taskRepository;

    public void getValidTask(TaskRequest request) {
        boolean existsByNameAndEventIdAndStatus = taskRepository.existsByNameAndEventIdAndStatus(request.getName(), request.getEventId());
        ValidationService.validateTaskExists(existsByNameAndEventIdAndStatus);
    }

    public List<Task> findByStatusTrueAndEventId(TaskRequest request) {
        return taskRepository.findByStatusTrueAndEventId(request.getEventId());
    }

    public List<TaskInfo> addTask(TaskRequest request) {
        getValidTask(request);
        Task task = taskMapper.taskRequestToTask(request);
        taskRepository.save(task);
        List<Task> tasks = findByStatusTrueAndEventId(request);
        return taskMapper.tasksToTaskInfos(tasks);
    }

    public void deleteTask(Integer taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        Boolean status = task.get().getStatus();
        task.get().setStatus(!status);
        taskRepository.save(task.get());
    }
}
