package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.date.EventDateInfo;
import com.example.vabatahtlikud.domain.event.task.TaskDateInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterToEventInfo implements Serializable {

    private List<TaskDateInfo> tasks;
    private List<EventDateInfo> eventDateInfos;
}
