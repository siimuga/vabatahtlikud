package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.*;
import com.example.vabatahtlikud.domain.event.category.Category;
import com.example.vabatahtlikud.domain.event.category.CategoryRepository;
import com.example.vabatahtlikud.domain.event.language.Language;
import com.example.vabatahtlikud.domain.event.language.LanguageRepository;
import com.example.vabatahtlikud.domain.event.location.Location;
import com.example.vabatahtlikud.domain.event.location.LocationRepository;
import com.example.vabatahtlikud.domain.event.location.country.County;
import com.example.vabatahtlikud.domain.event.location.country.CountyRepository;
import com.example.vabatahtlikud.domain.event.picture.PictureData;
import com.example.vabatahtlikud.domain.event.picture.PictureDataRepository;
import com.example.vabatahtlikud.domain.event.picture.PictureDto;
import com.example.vabatahtlikud.domain.event.picture.PictureService;
import com.example.vabatahtlikud.domain.event.task.*;
import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.domain.user.user.UserRepository;
import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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

//        Optional<PictureData> defaultPicture = pictureDataRepository.findById(1);
//         if (request.getPictureData().equals("")) {
//             event.setPictureData(defaultPicture.get());
//         } else {
//             PictureData picture = new PictureData();
//             byte[] byteData = request.getPictureData().getBytes(StandardCharsets.UTF_8);
//             picture.setData(byteData);
//             event.setPictureData(picture);
//         }

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
        event.get().setLanguage(language.get());
        event.get().setLink(request.getLink());
        event.get().setCategory(category.get());
        event.get().setStartDate(request.getStartDate());
        event.get().setEndDate(request.getEndDate());
        event.get().setLocation(location);
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

    public List<EventSearchResponse> findEventsByCategoryAndCounty(EventSearchRequest request) {
        EventSearchResponse eventSearchResponse = new EventSearchResponse();
        Optional<PictureData> defaultPicture = pictureDataRepository.findById(1);
        if (request.getCategoryId() == 5 && request.getCountyId() == 16) {
            List<Event> events = eventRepository.findAll();
//            for (Event event : events) {
//                if (event.getPictureData().getId() == 1) {
//                    eventSearchResponse.setHasPicture(false);
//                }
//                if (!event.getPictureData().equals(null)) {
//                    event.setPictureData(defaultPicture.get());
//                    eventSearchResponse.setHasPicture(false);
//                }
//            }
            return eventMapper.eventsToEventSearchResponses(events);
        }
        if (request.getCategoryId() == 5) {
            List<Event> events = eventRepository.findByCountyId(request.getCountyId());
            return eventMapper.eventsToEventSearchResponses(events);
        }
        if (request.getCountyId() == 16) {
            List<Event> events = eventRepository.findByCategoryId(request.getCategoryId());
            return eventMapper.eventsToEventSearchResponses(events);
        }
        List<Event> events = eventRepository.findByCategoryIdAndCountyId(request.getCategoryId(), request.getCountyId());
        return eventMapper.eventsToEventSearchResponses(events);
    }


}
