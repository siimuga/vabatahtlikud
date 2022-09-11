package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.task.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EventService {


    @Resource
    private TaskService taskService;


    public List<TaskInfo> addTask(TaskRequest request) {
        return taskService.addTask(request);
    }

}
