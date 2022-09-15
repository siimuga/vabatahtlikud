package com.example.vabatahtlikud.domain.event.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDateInfo implements Serializable {
    private LocalDate date;
    private Integer volunteersRequired;
    private Integer volunteersAssigned;
    private Boolean isActive;
}
