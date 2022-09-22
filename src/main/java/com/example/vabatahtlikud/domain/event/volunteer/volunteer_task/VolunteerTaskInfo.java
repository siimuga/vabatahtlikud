package com.example.vabatahtlikud.domain.event.volunteer.volunteer_task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VolunteerTaskInfo implements Serializable {
    private Integer volunteerId;
    private Integer taskId;
}
