package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ActiveEventInfo implements Serializable {
    private Integer seqNr;
    private Integer eventId;
    private String roleName;
    private String eventName;
    private Integer volunteersRequired;
    private Integer volunteersAttended;
}
