package com.example.vabatahtlikud.domain.event.volunteer;

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
    public void addRegistration(@RequestBody VolunteerRequest request) {
        volunteerService.addRegistration(request);
    }

    @PostMapping("/task")
    @Operation(summary = "Lisa ülesanded üritusele registreerimisel")
    public void addTasksToVolunteer(@RequestBody List<VolunteerTaskInfo> volunteerTaskInfos) {
        volunteerService.addTasksToVolunteer(volunteerTaskInfos);
    }
}