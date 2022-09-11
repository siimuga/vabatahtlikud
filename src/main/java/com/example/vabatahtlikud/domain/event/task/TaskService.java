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
        boolean taskExists = taskRepository.existsByNameAndStatusTrue(request.getName());
        ValidationService.validateTaskExists(taskExists);
    }

    public List<Task> findAllByStatusTrue() {
        return taskRepository.findAllByStatusTrue();
    }

    public List<TaskInfo> addTask(TaskRequest request) {
        getValidTask(request);
        Task task = taskMapper.taskRequestToTask(request);
        taskRepository.save(task);
        List<Task> tasks = findAllByStatusTrue();
        return taskMapper.tasksToTaskInfos(tasks);
    }
}
