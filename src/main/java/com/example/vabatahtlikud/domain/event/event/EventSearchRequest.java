package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

@Data
public class EventSearchRequest {

    private Integer countyId;
    private Integer categoryId;
}
