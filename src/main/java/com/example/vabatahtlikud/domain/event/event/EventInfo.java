package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

@Data
public class EventInfo {
    private Integer seqNr;
    private Integer eventId;
    private String pictureData;
    private String eventName;
    private Integer volunteersRequired;
    private Integer volunteersAttended;
    private Boolean hasPicture;
    private String status;
}
