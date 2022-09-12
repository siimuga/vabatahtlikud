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
import com.example.vabatahtlikud.domain.event.picture.PictureDto;
import com.example.vabatahtlikud.domain.event.picture.PictureService;
import com.example.vabatahtlikud.domain.event.task.*;
import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.domain.user.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<TaskInfo> addTask(TaskRequest request) {
        return taskService.addTask(request);
    }

    public List<AdditionalInfoResponse> addInfo(AdditionalInfoRequest request) {
        return additionalInfoService.addInfo(request);
    }

    public void deleteTask(Integer taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        Boolean status = task.get().getStatus();
        task.get().setStatus(!status);
        taskRepository.save(task.get());
    }

    public void deleteAdditionalInfo(Integer additionalInfoId) {
        Optional<AdditionalInfo> additionalInfo = additionalInfoRepository.findById(additionalInfoId);
        Boolean status = additionalInfo.get().getStatus();
        additionalInfo.get().setStatus(!status);
        additionalInfoRepository.save(additionalInfo.get());
    }

    public void addPicture(PictureDto pictureAsBase64) {
        pictureService.addPicture(pictureAsBase64);
    }

    public void addEvent(EventRequest request) {
        Event event  = eventMapper.eventRequestToEvent(request);
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
}
