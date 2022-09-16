package com.example.vabatahtlikud.domain.event.volunteer;

import lombok.Data;

import java.io.Serializable;

@Data
public class VolunteerRequest implements Serializable {
    private final Integer userId;
    private final Integer eventId;
    private final Integer volunteersSize;
    private final String info;
}
