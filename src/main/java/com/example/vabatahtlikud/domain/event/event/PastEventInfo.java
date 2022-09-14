package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

import java.io.Serializable;

@Data
public class PastEventInfo implements Serializable {
    private final Integer id;
    private final String userRoleName;
    private final String eventName;
    private final Integer volunteersRequired;
    private Integer volunteersAttended;
}
