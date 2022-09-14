package com.example.vabatahtlikud.admin;

import com.example.vabatahtlikud.domain.event.event.EventService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {

    @Resource
    private EventService eventService;
    public void validateEvent(Integer eventId) {
        eventService.validateEvent(eventId);
    }
}
