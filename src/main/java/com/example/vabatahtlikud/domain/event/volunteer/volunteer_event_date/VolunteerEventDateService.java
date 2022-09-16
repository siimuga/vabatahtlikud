package com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VolunteerEventDateService {

    @Resource
    private VolunteerEventDateMapper volunteerEventDateMapper;

    @Resource
    private VolunteerEventDateRepository volunteerEventDateRepository;


    public void addDatesToVolunteer(List<VolunteerEventDateInfo> volunteerEventDateInfos) {
        List<VolunteerEventDate> volunteerEventDates = volunteerEventDateMapper.volunteerEventDateInfosToVolunteerEventDates((volunteerEventDateInfos));
        volunteerEventDateRepository.saveAll(volunteerEventDates);
    }
}
