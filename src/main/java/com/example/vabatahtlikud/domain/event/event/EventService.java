package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.*;
import com.example.vabatahtlikud.domain.event.category.Category;
import com.example.vabatahtlikud.domain.event.category.CategoryInfo;
import com.example.vabatahtlikud.domain.event.category.CategoryRepository;
import com.example.vabatahtlikud.domain.event.category.CategoryService;
import com.example.vabatahtlikud.domain.event.date.EventDateInfo;
import com.example.vabatahtlikud.domain.event.date.EventDateService;
import com.example.vabatahtlikud.domain.event.language.Language;
import com.example.vabatahtlikud.domain.event.language.LanguageInfo;
import com.example.vabatahtlikud.domain.event.language.LanguageRepository;
import com.example.vabatahtlikud.domain.event.language.LanguageService;
import com.example.vabatahtlikud.domain.event.location.Location;
import com.example.vabatahtlikud.domain.event.location.LocationRepository;
import com.example.vabatahtlikud.domain.event.location.country.County;
import com.example.vabatahtlikud.domain.event.location.country.CountyInfo;
import com.example.vabatahtlikud.domain.event.location.country.CountyRepository;
import com.example.vabatahtlikud.domain.event.location.country.CountyService;
import com.example.vabatahtlikud.domain.event.picture.*;
import com.example.vabatahtlikud.domain.event.task.*;
import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.domain.user.user.UserRepository;
import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
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

    @Resource
    private PictureService pictureService;

    @Resource
    private EventMapper eventMapper;

    @Resource
    private EventRepository eventRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private LanguageRepository languageRepository;

    @Resource
    private LocationRepository locationRepository;

    @Resource
    private CountyRepository countyRepository;

    @Resource
    private PictureDataRepository pictureDataRepository;

    @Resource
    private CategoryService categoryService;

    @Resource
    private CountyService countyService;

    @Resource
    private LanguageService languageService;

    @Resource
    private EventDateService eventDateService;

    public List<TaskInfo> addTask(TaskRequest request) {
        return taskService.addTask(request);
    }

    public List<AdditionalInfoResponse> addInfo(AdditionalInfoRequest request) {
        return additionalInfoService.addInfo(request);
    }

    public void deleteTask(Integer taskId) {
        taskService.deleteTask(taskId);
    }

    public void deleteAdditionalInfo(Integer additionalInfoId) {
        additionalInfoService.deleteAdditionalInfo(additionalInfoId);
    }

    public void addPicture(PictureDto pictureAsBase64) {
        pictureService.addPicture(pictureAsBase64);
    }

    public void addEvent(EventRequest request) {
        ValidationService.validateDates(request.getStartDate(), request.getEndDate());
        ValidationService.validateVolunteersRequired(request.getVolunteersRequired());
        Event event = eventMapper.eventRequestToEvent(request);
        Optional<User> user = userRepository.findById(request.getUserId());
        Optional<Category> category = categoryRepository.findById(request.getCategoryId());
        Optional<Language> language = languageRepository.findById(request.getLanguageId());
        Optional<County> county = countyRepository.findById(request.getLocationCountyId());

        Location location = new Location();
        location.setAddress(request.getLocationAddress());
        location.setCounty(county.get());
        locationRepository.save(location);
        event.setUser(user.get());
        event.setCategory(category.get());
        event.setLanguage(language.get());
        event.setLocation(location);
        eventRepository.save(event);
    }

    public void updateEvent(EventUpdateRequest request) {
        ValidationService.validateDates(request.getStartDate(), request.getEndDate());
        ValidationService.validateVolunteersRequired(request.getVolunteersRequired());
        Optional<Event> event = eventRepository.findById(request.getEventId());
        Location location = event.get().getLocation();
        location.setAddress(request.getLocationAddress());
        Optional<County> county = countyRepository.findById(request.getLocationCountyId());
        location.setCounty(county.get());
        locationRepository.save(location);

        Optional<Language> language = languageRepository.findById(request.getLanguageId());
        Optional<Category> category = categoryRepository.findById(request.getCategoryId());
        language.get().setId(request.getLanguageId());
        category.get().setId(request.getCategoryId());
        event.get().setVolunteersRequired(request.getVolunteersRequired());
        event.get().setEventName(request.getEventName());
        event.get().setStartDate(request.getStartDate());
        event.get().setEndDate(request.getEndDate());
        event.get().setCategory(category.get());
        event.get().setLocation(location);
        event.get().setLanguage(language.get());
        event.get().setLink(request.getLink());
        eventRepository.save(event.get());
    }

    public AddEventResponse findTasksAndAddInfos(Integer eventId) {
        AddEventResponse addEventResponses = new AddEventResponse();
        Optional<Event> event = eventRepository.findById(eventId);
        List<Task> tasks = findTasksById(event.get().getId());
        List<AdditionalInfo> additionalInfos = findAdditionalInfosById(event.get().getId());
        addEventResponses.setTasks(tasks);
        addEventResponses.setAdditionalInfos(additionalInfos);
        addEventResponses.setEventId(eventId);
        return addEventResponses;
    }

    private List<AdditionalInfo> findAdditionalInfosById(Integer eventId) {
        return additionalInfoRepository.findByStatusTrueAndEventId(eventId);
    }

    public List<Task> findTasksById(Integer eventId) {
        return taskRepository.findByStatusTrueAndEventId(eventId);
    }


    public void deleteEvent(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        event.get().setStatus("d");
        eventRepository.save(event.get());
    }


    public List<EventInfo> findAllEvents() {
        List<Event> events = eventRepository.findAll("v");
        List<EventInfo> eventInfos = updateEventInfos(events);
        return eventInfos;   //volunteersAttended on puudu
    }

    public List<EventInfo> updateEventInfos(List<Event> events) {
        List<EventInfo> eventInfos = eventMapper.eventsToEventInfos(events);
        for (EventInfo eventInfo : eventInfos) {
            Optional<PictureData> picture = pictureDataRepository.findByEventId(eventInfo.getEventId());
            if (picture.isPresent()) {
                String pictureBase64 = new String(picture.get().getData(), StandardCharsets.UTF_8);
                eventInfo.setHasPicture(true);
                eventInfo.setPictureData(pictureBase64);
            } else {
                eventInfo.setHasPicture(false);
            }
            eventInfo.setVolunteersAttended(99);  //SIIN VAJA MUUTA
        }
        return eventInfos;
    }

    public List<EventInfo> findByCategories(Integer categoryId) {
        List<Event> events = eventRepository.findByCategoryId(categoryId, "v");
        return updateEventInfos(events);
    }

    public List<EventInfo> findByCounties(Integer countyId) {
        List<Event> events = eventRepository.findByCountyId(countyId, "v");
        return updateEventInfos(events);
    }

    public List<EventInfo> findEventsByCategoryAndCounty(EventSearchRequest request) {
        List<Event> events = eventRepository.findByCategoryIdAndCountyId(request.getCategoryId(), request.getCountyId(), "v");
        return updateEventInfos(events);
    }

    public List<CategoryInfo> findAllCategories() {
        return categoryService.findAllCategories();
    }

    public List<CountyInfo> findAllCounties() {
        return countyService.findAllCounties();
    }

    public List<LanguageInfo> findAllLanguages() {
        return languageService.findAllLanguages();
    }

    public void validateEvent(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        event.get().setStatus("v");
        eventRepository.save(event.get());
    }

    public List<PastEventInfo> findAllPastEvents() {
        List<Event> events = eventRepository.findByAfterEndDate(LocalDate.now());
        List<PastEventInfo> pastEventInfos = eventMapper.eventsToPastEventInfos(events);
        for (PastEventInfo pastEventInfo : pastEventInfos) {
            pastEventInfo.setVolunteersAttended(999);
            pastEventInfo.setId(pastEventInfos.indexOf(pastEventInfo) + 1);
        }
        return pastEventInfos;
    }

    public void updatePastEventsStatuses() {
        List<Event> events = eventRepository.findByAfterEndDateAndPublished(LocalDate.now(), "v");
        for (Event event : events) {
            event.setStatus("e");
            eventRepository.save(event);
        }
    }

    public List<PastEventInfo> findAllPastEventsByUser(Integer userId) {
        List<Event> events = eventRepository.findByAfterEndDateByUser(userId, "e");
        List<PastEventInfo> pastEventInfos = eventMapper.eventsToPastEventInfos(events);
        int i = 0;
        for (PastEventInfo pastEventInfo : pastEventInfos) {
            pastEventInfo.setVolunteersAttended(999);
            pastEventInfo.setId(i);
            i++;
        }
        return pastEventInfos;
    }

    public RegisterToEventInfo findDatesAndTasksByEvent(Integer eventId) {
        RegisterToEventInfo registerToEventInfo = new RegisterToEventInfo();
        List<TaskDateInfo> tasks = taskService.findAllEventTaskInfos(eventId);
        registerToEventInfo.setTasks(tasks);
        List<EventDateInfo> dateInfos = eventDateService.findAllEventDateInfos(eventId);
        registerToEventInfo.setEventDateInfos(dateInfos);
        return registerToEventInfo;

    }


    public List<TaskInfo> findTasksByEvent(Integer eventId) {
        return taskService.findTasksByEvent(eventId);
    }


    public List<AdditionalInfoResponse> findAdditionalInfosByEvent(Integer eventId) {
        return additionalInfoService.findAdditionalInfosByEvent(eventId);
    }

    public EventViewInfo findEventMainInfo(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        EventViewInfo eventViewInfo = new EventViewInfo();
        eventViewInfo.setEventName(event.get().getEventName());
        eventViewInfo.setLanguageName(event.get().getLanguage().getName());
        eventViewInfo.setLink(event.get().getLink());
        eventViewInfo.setLocationAddress(event.get().getLocation().getAddress());
        eventViewInfo.setLocationCountyName(event.get().getLocation().getCounty().getName());
        eventViewInfo.setVolunteersRequired(event.get().getVolunteersRequired());
        eventViewInfo.setVolunteersAttended(99);
        eventViewInfo.setStartDate(event.get().getStartDate());
        eventViewInfo.setEndDate(event.get().getEndDate());
        Optional<PictureData> picture = pictureDataRepository.findByEventId(eventId);
        if (picture.isPresent()) {
            String pictureBase64 = new String(picture.get().getData(), StandardCharsets.UTF_8);
            eventViewInfo.setHasPicture(true);
            eventViewInfo.setPictureData(pictureBase64);
        } else {
            eventViewInfo.setHasPicture(false);
        }
        return eventViewInfo;
    }

    public List<EventInfo> findAllRegistredEvents() {
        List<Event> events = eventRepository.findRegistredEvents(LocalDate.now(), "c", "v");
        return eventMapper.eventsToEventInfos(events);
    }
}
