package com.example.vabatahtlikud.domain.event.volunteer;

import lombok.Data;

import java.util.List;

@Data
public class VolunteerRequestDatas {
        private Integer volunteerId;
    private List<Integer> volunteerEventDateInfos;
    private List<Integer> volunteerTaskInfos;
}
