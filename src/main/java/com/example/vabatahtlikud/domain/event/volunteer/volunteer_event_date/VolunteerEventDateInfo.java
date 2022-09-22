package com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VolunteerEventDateInfo implements Serializable {
    private Integer volunteerId;
    private Integer eventDateId;
}
