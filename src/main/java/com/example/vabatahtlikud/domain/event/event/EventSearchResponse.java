package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

@Data
public class EventSearchResponse {
    private Integer eventId;
    private byte[] pictureData;
    private String eventName;
    private Integer volunteersRequired;
    private Integer volunteersAttended;
    private Boolean hasPicture;
}
