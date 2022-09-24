package com.example.vabatahtlikud.admin;

import com.example.vabatahtlikud.domain.event.event.EventInfo;
import com.example.vabatahtlikud.domain.event.event.EventService;
import com.example.vabatahtlikud.domain.event.event.PastEventInfo;
import com.example.vabatahtlikud.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {

    @Resource
    private EventService eventService;

    @Resource
    private UserService userService;

    public void validateEvent(Integer eventId) {
        eventService.validateEvent(eventId);
    }

    public List<PastEventInfo> findAllPastEvents() {
        return eventService.findAllPastEvents();

    }

    public List<EventInfo> findAllRegisteredEvents() {
        return eventService.findAllRegisteredEvents();
    }

    public List<AdminUserRequest> findAllUserInfos() {
        return userService.findAllUserInfos();
    }

    public void activateUser(Integer userId) {
        userService.activateUser(userId);
    }
}
