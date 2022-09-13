package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventUpdateRequest {
    private Integer eventId;
    private Integer categoryId;
    private String eventName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer locationCountyId;
    private String link;
    private String locationAddress;
    private Integer volunteersRequired;
    private Integer languageId;
    private Integer eventRegisterId;
}
