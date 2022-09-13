package com.example.vabatahtlikud.domain.event.task;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskRequest implements Serializable {
    private String name;
    private Integer eventId;
}
