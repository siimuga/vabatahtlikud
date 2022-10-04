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
import com.example.vabatahtlikud.domain.event.volunteer.Volunteer;
import com.example.vabatahtlikud.domain.event.volunteer.VolunteerRepository;
import com.example.vabatahtlikud.domain.event.volunteer.VolunteerService;
import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.domain.user.user.UserRepository;
import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class EventService {

    @Resource
    private TaskService taskService;

    @Resource
    private AdditionalInfoService additionalInfoService;

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


    @Resource
    private VolunteerRepository volunteerRepository;

    @Resource
    private VolunteerService volunteerService;

    public List<TaskInfo> addTask(TaskRequest request) {
        return taskService.addTask(request);
    }

    public List<AdditionalInfoResponse> addInfo(AdditionalInfoInfo request) {
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

    public Integer addEvent(EventRequest request) {
        ValidationService.validateDates(request.getStartDate(), request.getEndDate());
        ValidationService.validateVolunteersRequired(request.getVolunteersRequired());
        Event event = eventMapper.eventRequestToEvent(request);
        Optional<User> user = userRepository.findById(request.getUserId());
        Category category = categoryRepository.findByName(request.getCategoryName());
        Language language = languageRepository.findByName(request.getLanguageName());
        County county = countyRepository.findByName(request.getLocationCountyName());

        Location location = new Location();
        location.setAddress(request.getLocationAddress());
        location.setCounty(county);
        locationRepository.save(location);
        event.setUser(user.get());
        event.setCategory(category);
        event.setLanguage(language);
        event.setLocation(location);

        eventRepository.save(event);
        eventDateService.addDateInfos(event.getId());
        Event e = eventRepository.findByEventName(request.getEventName());
        return e.getId();
    }

    public void updateEvent(EventViewInfo request) {
        ValidationService.validateDates(request.getStartDate(), request.getEndDate());
        ValidationService.validateVolunteersRequired(request.getVolunteersRequired());
        Optional<Event> event = eventRepository.findById(request.getEventId());
        Location location = event.get().getLocation();
        location.setAddress(request.getLocationAddress());
        County county = countyRepository.findByName(request.getLocationCountyName());
        location.setCounty(county);
        locationRepository.save(location);

        Language language = languageRepository.findByName(request.getLanguageName());
        Category category = categoryRepository.findByName(request.getCategoryName());
        language.setName(request.getLanguageName());
        category.setName(request.getCategoryName());
        event.get().setVolunteersRequired(request.getVolunteersRequired());
        event.get().setEventName(request.getEventName());
        event.get().setStartDate(request.getStartDate());
        event.get().setEndDate(request.getEndDate());
        event.get().setCategory(category);
        event.get().setLocation(location);
        event.get().setLanguage(language);
        event.get().setLink(request.getLink());
        eventRepository.save(event.get());
    }

    public AddEventResponse findTasksAndAddInfos(Integer eventId) {
        AddEventResponse addEventResponses = new AddEventResponse();
        List<TaskDateInfo> tasks = taskService.findAllEventTaskInfos(eventId);
        List<AdditionalInfoRequest> additionalInfos = additionalInfoService.findAdditionalInfoRequestsByEvent(eventId);
        addEventResponses.setTasks(tasks);
        addEventResponses.setAdditionalInfos(additionalInfos);
        addEventResponses.setEventId(eventId);
        return addEventResponses;
    }

    public void deleteEvent(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        event.get().setStatus("d");
        eventRepository.save(event.get());
    }


    public List<EventInfo> findAllEvents() {
        List<Event> events = eventRepository.findAll("v");
        return updateEventInfos(events);
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
            String eventName = eventInfo.getEventName();
            eventInfo.setVolunteersAttended(getAttendance(eventName));
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
            String eventName = pastEventInfo.getEventName();
            pastEventInfo.setVolunteersAttended(getAttendance(eventName));
            pastEventInfo.setSeqNr(pastEventInfos.indexOf(pastEventInfo) + 1);
        }
        return pastEventInfos;
    }

    private Integer getAttendance(String eventName) {
        Event event = eventRepository.findByEventName(eventName);
        Integer eventId = event.getId();
        return volunteerService.findAttendanceByEventId(eventId);
    }

    public void updatePastEventsStatuses() {
        List<Event> events = eventRepository.findByAfterEndDateAndPublished(LocalDate.now(), "v");
        for (Event event : events) {
            event.setStatus("e");
            eventRepository.save(event);
        }
    }

    public List<PastEventInfo> findAllPastEventsByUser(Integer userId) {
        List<Event> pastVolunteerEvents = new ArrayList<>();
        List<Event> volunteerEvents = findVolunteerEventsByUser(userId);
        for (Event volunteerEvent : volunteerEvents) {
            if (volunteerEvent.getEndDate().isBefore(LocalDate.now())) {
                pastVolunteerEvents.add(volunteerEvent);
            }
        }
        List<Event> events = eventRepository.findByAfterEndDateByUser(userId, "e");
        events.addAll(pastVolunteerEvents);
        events.sort(Comparator.comparing(Event::getEndDate));
        Collections.reverse(events);
        List<PastEventInfo> pastEventInfos = eventMapper.eventsToPastEventInfos(events);
        for (PastEventInfo pastEventInfo : pastEventInfos) {
            String eventName = pastEventInfo.getEventName();
            Event event = eventRepository.findByEventName(eventName);
            pastEventInfo.setVolunteersAttended(getAttendance(eventName));
            pastEventInfo.setSeqNr(pastEventInfos.indexOf(pastEventInfo) + 1);
            Integer organizerId = event.getUser().getId();
            if (Objects.equals(organizerId, userId)) {
                pastEventInfo.setRoleName("korraldaja");
            } else
                pastEventInfo.setRoleName("vabatahtlik");
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
        eventViewInfo.setEventId(eventId);
        eventViewInfo.setEventName(event.get().getEventName());
        eventViewInfo.setLanguageName(event.get().getLanguage().getName());
        eventViewInfo.setLink(event.get().getLink());
        eventViewInfo.setLocationAddress(event.get().getLocation().getAddress());
        eventViewInfo.setLocationCountyName(event.get().getLocation().getCounty().getName());
        eventViewInfo.setVolunteersRequired(event.get().getVolunteersRequired());
        eventViewInfo.setVolunteersAttended(getAttendance(event.get().getEventName()));
        eventViewInfo.setStartDate(event.get().getStartDate());
        eventViewInfo.setEndDate(event.get().getEndDate());
        eventViewInfo.setCategoryName(event.get().getCategory().getName());
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

    public List<EventInfo> findAllRegisteredEvents() {
        List<Event> events = eventRepository.findRegistredEvents(LocalDate.now(), "c", LocalDate.now(), "v");
        List<EventInfo> eventInfos = eventMapper.eventsToEventInfos(events);
        for (EventInfo eventInfo : eventInfos) {
            String eventName = eventInfo.getEventName();
            eventInfo.setVolunteersAttended(getAttendance(eventName));
            eventInfo.setSeqNr(eventInfos.indexOf(eventInfo) + 1);
        }
        return eventInfos;
    }

    public List<EventDateInfo> findAllEventDateInfos(Integer eventId) {
        return eventDateService.findAllEventDateInfos(eventId);
    }

    public List<ActiveEventInfo> findAllActiveEventsByUser(Integer userId) {
        List<Event> volunteerEvents = findVolunteerEventsByUser(userId);
        List<Event> events = eventRepository.findAllActiveEventsByUser(userId, "c", userId, "v");
        events.addAll(volunteerEvents);
        events.sort(Comparator.comparing(Event::getStartDate));
        List<ActiveEventInfo> activeEventInfos = eventMapper.eventsToActiveEventInfos(events);
        for (ActiveEventInfo activeEventInfo : activeEventInfos) {
            String eventName = activeEventInfo.getEventName();
            Event event = eventRepository.findByEventName(eventName);
            activeEventInfo.setVolunteersAttended(getAttendance(eventName));
            activeEventInfo.setSeqNr(activeEventInfos.indexOf(activeEventInfo) + 1);
            activeEventInfo.setEventId(event.getId());
            Integer organizerId = event.getUser().getId();
            if (Objects.equals(organizerId, userId)) {
                activeEventInfo.setRoleName("korraldaja");
            } else
                activeEventInfo.setRoleName("vabatahtlik");
        }
        return activeEventInfos;
    }

    public List<Event> findVolunteerEventsByUser(Integer userId) {
        List<Event> events = new ArrayList<>();
        List<Volunteer> volunteers = volunteerRepository.findByUserIdAndStatus(userId);
        for (Volunteer volunteer : volunteers) {
            Event event = volunteer.getEvent();
            if (event.getEndDate().isAfter(LocalDate.now())) {
                events.add(event);
            }
        }
        return events;
    }

    public void deleteParticipation(Integer userId, Integer eventId) {
        volunteerService.deleteParticipation(userId, eventId);
    }


    public Boolean checkVolunteerEventByUser(Integer userId, Integer eventId) {
       return volunteerService.checkVolunteerEventByUser(userId, eventId);
    }


    public Boolean checkOrganizerEventByUser(Integer userId, Integer eventId) {
        return eventRepository.existsByUserIdAndEventId(eventId, userId, "c", eventId, userId,"v");
    }
}
