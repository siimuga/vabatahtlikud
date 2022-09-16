package com.example.vabatahtlikud.domain.event.volunteer.volunteer_task;

import lombok.Data;

import java.io.Serializable;

@Data
public class VolunteerTaskInfo implements Serializable {
    private Integer volunteerId;
    private Integer taskId;
}
