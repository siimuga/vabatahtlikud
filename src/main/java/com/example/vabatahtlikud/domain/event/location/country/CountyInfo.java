package com.example.vabatahtlikud.domain.event.location.country;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountyInfo implements Serializable {
    private  String name;
    private Integer countyId;
}
