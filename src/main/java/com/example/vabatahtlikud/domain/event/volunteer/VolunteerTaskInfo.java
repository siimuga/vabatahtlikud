package com.example.vabatahtlikud.domain.event.volunteer;

import lombok.Data;

import java.io.Serializable;

@Data
public class VolunteerTaskInfo implements Serializable {
    private Integer volunteerId;
    private Integer taskId;
    private String taskName;
    private Boolean taskStatus;
}
