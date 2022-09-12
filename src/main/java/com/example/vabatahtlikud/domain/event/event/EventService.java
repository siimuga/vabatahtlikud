package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.*;
import com.example.vabatahtlikud.domain.event.task.*;
import com.example.vabatahtlikud.domain.user.user.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {


    @Resource
    private TaskService taskService;

    @Resource
    private AdditionalInfoService additionalInfoService;

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private AdditionalInfoRepository additionalInfoRepository;

    public List<TaskInfo> addTask(TaskRequest request) {
        return taskService.addTask(request);
    }

    public List<AdditionalInfoResponse> addInfo(AdditionalInfoRequest request) {
        return additionalInfoService.addInfo(request);
    }

    public void deleteTask(Integer taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        Boolean status = task.get().getStatus();
        task.get().setStatus(!status);
        taskRepository.save(task.get());
    }

    public void deleteAdditionalInfo(Integer additionalInfoId) {
        Optional<AdditionalInfo> additionalInfo = additionalInfoRepository.findById(additionalInfoId);
        Boolean status = additionalInfo.get().getStatus();
        additionalInfo.get().setStatus(!status);
        additionalInfoRepository.save(additionalInfo.get());
    }
}
