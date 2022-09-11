package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoRequest;
import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoResponse;
import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoService;
import com.example.vabatahtlikud.domain.event.task.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EventService {


    @Resource
    private TaskService taskService;

    @Resource
    private AdditionalInfoService additionalInfoService;

    public List<TaskInfo> addTask(TaskRequest request) {
        return taskService.addTask(request);
    }

    public List<AdditionalInfoResponse> addInfo(AdditionalInfoRequest request) {
        return additionalInfoService.addInfo(request);
    }
}
