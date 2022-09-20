package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoInfo;
import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoRequest;
import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoResponse;
import com.example.vabatahtlikud.domain.event.category.CategoryInfo;
import com.example.vabatahtlikud.domain.event.date.EventDateInfo;
import com.example.vabatahtlikud.domain.event.date.EventDateService;
import com.example.vabatahtlikud.domain.event.language.LanguageInfo;
import com.example.vabatahtlikud.domain.event.location.country.CountyInfo;
import com.example.vabatahtlikud.domain.event.picture.PictureDto;
import com.example.vabatahtlikud.domain.event.task.TaskInfo;
import com.example.vabatahtlikud.domain.event.task.TaskRequest;
import com.example.vabatahtlikud.domain.event.volunteer.VolunteerDeleteRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Resource
    private EventService eventService;

    @Resource
    private EventDateService eventDateService;

    @PostMapping("/task")
    @Operation(summary = "Lisab uue tööülesande")
    public List<TaskInfo> addTask(@RequestBody TaskRequest request) {
        return eventService.addTask(request);
    }

    @PostMapping("/additional/info")
    @Operation(summary = "Lisab uue lisainfo")
    public List<AdditionalInfoResponse> addInfo(@RequestBody AdditionalInfoInfo request) {
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

    @PostMapping("event")
    @Operation(summary = "Ürituse lisamine")
    public void addEvent(@RequestBody EventRequest request) {
        eventService.addEvent(request);
    }

    @PatchMapping("/event")
    @Operation(summary = "Muuda ürituse andmeid")
    public void updateEvent(@RequestBody EventUpdateRequest request) {
        eventService.updateEvent(request);
    }

    @GetMapping("/task/addinfo")
    @Operation(summary = "Kuva tööülesanded ja lisainfo ürituse kohta")
    public AddEventResponse findTasksAndAddInfos(@RequestParam Integer eventId) {
        return eventService.findTasksAndAddInfos(eventId);
    }

    @DeleteMapping("/event")
    @Operation(summary = "Ürituse kustutamine ehk staatuse muutmine")
    public void deleteEvent(@RequestParam Integer eventId) {
        eventService.deleteEvent(eventId);
    }


    @GetMapping("/events")
    @Operation(summary = "Kuva kõik kinnitatud üritused")
    public List<EventInfo> findAllEvents() {
        return eventService.findAllEvents();
    }


    @GetMapping("/events/county")
    @Operation(summary = "Kuva üritused maakonna järgi")
    public List<EventInfo> findByCounty(Integer countyId) {
        return eventService.findByCounties(countyId);
    }

    @GetMapping("/events/category")
    @Operation(summary = "Kuva üritused kategooria järgi")
    public List<EventInfo> findByCategory(Integer categoryId) {
        return eventService.findByCategories(categoryId);
    }

    @GetMapping("/event")
    @Operation(summary = "Ürituse otsimine maakonna ja kategooria järgi")
    public List<EventInfo> findEventsByCategoryAndCounty(EventSearchRequest request) {
        return eventService.findEventsByCategoryAndCounty(request);
    }

    @GetMapping("/category")
    @Operation(summary = "Kuva kõik valdkonnad")
    public List<CategoryInfo> findAllCategories() {
        return eventService.findAllCategories();
    }

    @GetMapping("/county")
    @Operation(summary = "Kuva kõik maakonnad")
    public List<CountyInfo> findAllCounties() {
        return eventService.findAllCounties();
    }

    @GetMapping("/language")
    @Operation(summary = "Kuva kõik keeled")
    public List<LanguageInfo> findAllLanguages() {
        return eventService.findAllLanguages();
    }

    @PatchMapping("/date/check")
    @Operation(summary = "Uuenda kõik lõpetatud ürituste saatused")
    public void updatePastEventsStatuses() {
        eventService.updatePastEventsStatuses();
    }

    @GetMapping("event/user/history")
    @Operation(summary = "Kuva möödunud üritused kasutaja järgi")
    public List<PastEventInfo> findAllPastEventsByUser(Integer userId) {
        return eventService.findAllPastEventsByUser(userId);
    }

    @GetMapping("/event/register")
    @Operation(summary = "Kuva osalemise kuupäevad ja tööülesanded ürituse kohta")
    public RegisterToEventInfo findDatesAndTasksByEvent(Integer eventId) {
        return eventService.findDatesAndTasksByEvent(eventId);
    }

    @GetMapping("/event/task")
    @Operation(summary = "Kuva tööülesanded ürituse kohta")
    public List<TaskInfo> findTasksByEvent(Integer eventId) {
        return eventService.findTasksByEvent(eventId);
    }

    @GetMapping("/event/addinfo")
    @Operation(summary = "Kuva lisainfo ürituse kohta")
    public List<AdditionalInfoResponse> findAdditionalInfosByEvent(Integer eventId) {
        return eventService.findAdditionalInfosByEvent(eventId);
    }

    @GetMapping("event/main")
    @Operation(summary = "Kuva põhiinfo ürituse kohta")
    public EventViewInfo findEventMainInfo(Integer eventId) {
        return eventService.findEventMainInfo(eventId);
    }

    @GetMapping("event/date/range")
    @Operation(summary = "Saa info iga kuupäeva kohta üritusele")
    public List<EventDateInfo> findAllEventDateInfos(Integer eventId) {
        return eventService.findAllEventDateInfos(eventId);
    }

    @GetMapping("/event/user")
    @Operation(summary = "Kuva kõik selle kasutaja eelseisvad üritused")
    public List<ActiveEventInfo> findAllActiveEventsByUser(Integer userId) {
        return eventService.findAllActiveEventsByUser(userId);
    }

    @DeleteMapping("/event/cancel")
    @Operation(summary = "Tühista osalemine sellel üritusel")
    public void cancelParticipation(Integer userId, Integer eventId) {
        eventService.deleteParticipation(userId, eventId);
    }


    @PostMapping("/date/info/AINULT_TESTIMISEKS")
    @Operation(summary = "Lisa kuupäeva kaupa info andmebaasi")
    public void addDateInfos(Integer eventId) {
        eventDateService.addDateInfos(eventId);
    }
}