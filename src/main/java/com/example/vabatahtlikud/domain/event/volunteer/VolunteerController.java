package com.example.vabatahtlikud.domain.event.volunteer;

import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDateInfo;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_task.VolunteerTaskInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Resource
    private VolunteerService volunteerService;

    @PostMapping("/register")
    @Operation(summary = "Lisa uus registreerimine")
    public VolunteerResponse addRegistration(@RequestBody VolunteerRequest request) {
        return volunteerService.addRegistration(request);
    }

//    @PostMapping("/task")
//    @Operation(summary = "Lisa ülesanded üritusele registreerimisel")
//    public void addTasksToVolunteer(@RequestBody List<VolunteerTaskInfo> volunteerTaskInfos) {
//        volunteerService.addTasksToVolunteer(volunteerTaskInfos);
//    }
//
//    @PostMapping("/date")
//    @Operation(summary = "Lisa kuupäevad üritusele registreerimisel")
//    public void addDatesToVolunteer(@RequestBody List<VolunteerEventDateInfo> volunteerEventDateInfos) {
//        volunteerService.addDatesToVolunteer(volunteerEventDateInfos);
//    }
    @PostMapping("/register/new")
    @Operation(summary = "Lisa andmed registreerimiselt")
    public void addRegistrationDatas(@RequestBody VolunteerRequestDatas request) {
        volunteerService.addRegistrationDatas(request);
    }


}