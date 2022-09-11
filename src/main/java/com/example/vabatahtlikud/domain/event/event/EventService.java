package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.task.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EventService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private TaskRepository taskRepository;


    public List<TaskInfo> addTask(TaskRequest request) {
        Task task = taskMapper.taskRequestToTask(request);
        taskRepository.save(task);
        List<Task> tasks = getAllTasks();
        return taskMapper.tasksToTaskInfos(tasks);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
