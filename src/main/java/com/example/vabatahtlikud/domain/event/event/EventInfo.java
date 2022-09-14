package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

@Data
public class EventInfo {
    private Integer eventId;
    private String pictureData;
    private String eventName;
    private Integer volunteersRequired;
    private Integer volunteersAttended;
    private Boolean hasPicture;
}
