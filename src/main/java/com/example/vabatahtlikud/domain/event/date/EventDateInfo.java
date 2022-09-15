package com.example.vabatahtlikud.domain.event.date;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EventDateInfo implements Serializable {
    private Integer id;
    private Integer eventId;
    private LocalDate date;
    private Integer volunteersRequired;
    private Integer volunteersAssigned;
    private Boolean isActive;
}
