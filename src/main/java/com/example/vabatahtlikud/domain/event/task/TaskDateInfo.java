package com.example.vabatahtlikud.domain.event.task;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskDateInfo implements Serializable {
    private String name;
    private Boolean status;
    private Integer eventId;
}
