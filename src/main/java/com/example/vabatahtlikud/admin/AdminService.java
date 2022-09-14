package com.example.vabatahtlikud.admin;

import com.example.vabatahtlikud.domain.event.event.EventInfo;
import com.example.vabatahtlikud.domain.event.event.EventService;
import com.example.vabatahtlikud.domain.event.event.PastEventInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {

    @Resource
    private EventService eventService;

    public void validateEvent(Integer eventId) {
        eventService.validateEvent(eventId);
    }

    public List<PastEventInfo> findAllPastEvents() {
        return eventService.findAllPastEvents();

    }
}
