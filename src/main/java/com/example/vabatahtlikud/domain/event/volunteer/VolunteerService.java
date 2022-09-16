package com.example.vabatahtlikud.domain.event.volunteer;

import com.example.vabatahtlikud.domain.event.event.Event;
import com.example.vabatahtlikud.domain.event.event.EventRepository;
import com.example.vabatahtlikud.domain.event.task.Task;
import com.example.vabatahtlikud.domain.event.task.TaskRepository;
import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.domain.user.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {
    @Resource
    private VolunteerTaskRepository volunteerTaskRepository;

    @Resource
    private VolunteerMapper volunteerMapper;

    @Resource
    private UserRepository userRepository;

    @Resource
    private EventRepository eventRepository;


    @Resource
    private VolunteerRepository volunteerRepository;

    @Resource
    private VolunteerTaskMapper volunteerTaskMapper;

    @Resource
    private TaskRepository taskRepository;


    public void addRegistration(VolunteerRequest request) {
        Volunteer volunteer = volunteerMapper.volunteerRequestToVolunteer(request);
        Optional<User> user = userRepository.findById(request.getUserId());
        Optional<Event> event = eventRepository.findById(request.getEventId());
        volunteer.setEvent(event.get());
        volunteer.setUser(user.get());
        volunteerRepository.save(volunteer);
    }

    public void addTasksToVolunteer(List<VolunteerTaskInfo> volunteerTaskInfos) {
        List<VolunteerTask> volunteerTaskResult = new ArrayList<>();
        for (VolunteerTaskInfo volunteerTaskInfo : volunteerTaskInfos) {
            Optional<VolunteerTask> volunteerTask = volunteerTaskRepository.findById(volunteerTaskInfo.getTaskId());
            Optional<Volunteer> volunteer = volunteerRepository.findById(volunteerTaskInfo.getVolunteerId());
            Optional<Task> task = taskRepository.findById(volunteerTaskInfo.getTaskId());
            volunteerTask.get().setVolunteer(volunteer.get());
            volunteerTask.get().setTask(task.get());
            List<VolunteerTask> volunteerTasks = volunteerTaskMapper.volunteerTaskInfosToVolunteerTasks(volunteerTaskInfos);
            volunteerTaskResult.add(volunteerTask.get());
        }

//        List<VolunteerTask> volunteerTasks = volunteerTaskMapper.volunteerTaskInfosToVolunteerTasks(volunteerTaskInfos);
//        for (VolunteerTask volunteerTask : volunteerTasks) {
//            Optional<Volunteer> volunteer = volunteerRepository.findById(volunteerTask.getId());
//            Optional<Task> task = taskRepository.findById(volunteerTask.getTask().getId());
//            volunteerTask.setVolunteer(volunteer.get());
//            volunteerTask.setTask(task.get());
//            volunteerTaskRepository.save(volunteerTask);
//        }

    }
}
