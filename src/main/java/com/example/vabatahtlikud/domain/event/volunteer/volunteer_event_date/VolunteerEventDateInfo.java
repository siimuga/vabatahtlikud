package com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date;

import lombok.Data;

import java.io.Serializable;

@Data
public class VolunteerEventDateInfo implements Serializable {
    private final Integer volunteerId;
    private final Integer eventDateId;
}
