package com.example.vabatahtlikud.domain.event.volunteer;

import lombok.Data;

@Data
public class VolunteerDeleteRequest {
    private Integer userId;
    private Integer eventId;
}
