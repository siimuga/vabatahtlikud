package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfo;
import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoRepository;
import com.example.vabatahtlikud.domain.event.task.Task;
import com.example.vabatahtlikud.domain.event.task.TaskRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class EventRegisterService {

    @Resource
    private EventRegisterRepository eventRegisterRepository;

    @Resource
    private AdditionalInfoRepository additionalInfoRepository;

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private EventRepository eventRepository;

    public AddEventResponse findTasksAndAddInfos(Integer eventId) {
        AddEventResponse addEventResponses = new AddEventResponse();
        Optional<Event> event = eventRepository.findById(eventId);

        Optional<EventRegister> eventRegister = eventRegisterRepository.findById(event.get().getId());
        List<Task> tasks = findTasksById(eventRegister.get().getId());
        List<AdditionalInfo> additionalInfos = findAdditionalInfosById(eventRegister.get().getId());
        addEventResponses.setTasks(tasks);
        addEventResponses.setAdditionalInfos(additionalInfos);
        addEventResponses.setEventId(eventId);
        return addEventResponses;
    }

    private List<AdditionalInfo> findAdditionalInfosById(Integer eventRegisterId) {
        return additionalInfoRepository.findByStatusTrueAndEventRegisterId(eventRegisterId);
    }

    public List<Task> findTasksById(Integer eventRegisterId) {
        return taskRepository.findByStatusTrueAndEventRegisterId(eventRegisterId);
    }


}
