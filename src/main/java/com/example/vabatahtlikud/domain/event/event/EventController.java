package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoRequest;
import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoResponse;
import com.example.vabatahtlikud.domain.event.picture.PictureDto;
import com.example.vabatahtlikud.domain.event.task.TaskInfo;
import com.example.vabatahtlikud.domain.event.task.TaskRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Resource
    private EventService eventService;

    @PostMapping("/task")
    @Operation(summary = "Lisab uue tööülesande")
    public List<TaskInfo> addTask (@RequestBody TaskRequest request) {
        return eventService.addTask(request);
    }

    @PostMapping("/additional/info")
    @Operation(summary = "Lisab uue lisainfo")
    public List<AdditionalInfoResponse> addInfo (@RequestBody AdditionalInfoRequest request) {
        return eventService.addInfo(request);
    }

    @DeleteMapping("/delete/task")
    @Operation(summary = "Ürituse tööülesande kustutamine ehk staatuse muutmine")
    public void deleteTask(@RequestParam Integer taskId) {
        eventService.deleteTask(taskId);
    }

    @DeleteMapping("/delete/additional/info")
    @Operation(summary = "Ürituse lisainfo kustutamine ehk staatuse muutmine")
    public void deleteAdditionalInfo(@RequestParam Integer additionalInfoId) {
        eventService.deleteAdditionalInfo(additionalInfoId);
    }

    @PostMapping("/picture")
    @Operation(summary = "Pildi lisamine")
    public void addPicture(@RequestBody PictureDto picture) {
        eventService.addPicture(picture);
    }



}
