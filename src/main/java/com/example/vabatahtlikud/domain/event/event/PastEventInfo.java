package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

import java.io.Serializable;

@Data
public class PastEventInfo implements Serializable {
    private Integer seqNr;
    private String roleName;
    private String eventName;
    private Integer volunteersRequired;
    private Integer volunteersAttended;
    private String status;
}
