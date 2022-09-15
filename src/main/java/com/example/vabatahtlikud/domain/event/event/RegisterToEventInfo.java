package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.date.EventDateInfo;
import com.example.vabatahtlikud.domain.event.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterToEventInfo {

    private Integer eventId;
    private List<Task> tasks;
    private List<EventDateInfo> eventDateInfos;
}
