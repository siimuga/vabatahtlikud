package com.example.vabatahtlikud.domain.event.task;

import com.example.vabatahtlikud.domain.event.event.Event;
import com.example.vabatahtlikud.domain.event.event.EventRepository;
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

    @Resource
    private EventRepository eventRepository;

    public void getValidTask(TaskRequest request) {
        boolean existsByNameAndEventIdAndStatus = taskRepository.existsByNameAndEventIdAndStatus(request.getName(), request.getEventId());
        ValidationService.validateTask(existsByNameAndEventIdAndStatus, request.getName());
    }

    public List<Task> findByStatusTrueAndEventId(TaskRequest request) {
        return taskRepository.findByStatusTrueAndEventId(request.getEventId());
    }

    public List<TaskInfo> addTask(TaskRequest request) {
        getValidTask(request);
        Task task = taskMapper.taskRequestToTask(request);
        Optional<Event> event = eventRepository.findById(request.getEventId());
        task.setEvent(event.get());
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

    public List<TaskDateInfo> findAllEventTaskInfos(Integer eventId) {
        List<Task> tasks = taskRepository.findByStatusTrueAndEventId(eventId);
        return taskMapper.tasksToTaskDateInfos(tasks);
    }

    public List<TaskInfo> findTasksByEvent(Integer eventId) {
        List<Task> tasks = taskRepository.findByEventIdAndStatus(eventId);
        return taskMapper.tasksToTaskInfos(tasks);
    }
}
