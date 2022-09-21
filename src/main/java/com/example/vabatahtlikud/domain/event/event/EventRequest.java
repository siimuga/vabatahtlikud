package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EventRequest implements Serializable {
    private Integer userId;
    private String categoryName;
    private String eventName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String locationCountyName;
    private String link;
    private String locationAddress;
    private Integer volunteersRequired;
    private String languageName;
}
