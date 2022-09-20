package com.example.vabatahtlikud.domain.event.additional_info;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdditionalInfoRequest implements Serializable {
    private String name;
    private Integer addInfoId;
    private Boolean status;
    private Integer eventId;
}
