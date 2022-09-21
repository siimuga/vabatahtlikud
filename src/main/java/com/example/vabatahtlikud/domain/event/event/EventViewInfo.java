package com.example.vabatahtlikud.domain.event.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventViewInfo implements Serializable {
    private Integer eventId;
    private String eventName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String locationCountyName;
    private String locationAddress;
    private Integer volunteersRequired;
    private Integer volunteersAttended;
    private String languageName;
    private String link;
    private String pictureData;
    private Boolean hasPicture;
    private String categoryName;
}
