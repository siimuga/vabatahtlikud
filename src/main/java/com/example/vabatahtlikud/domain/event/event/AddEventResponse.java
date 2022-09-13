package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfo;
import com.example.vabatahtlikud.domain.event.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddEventResponse implements Serializable {
    private Integer eventId;
    private List<Task> tasks;
    private List<AdditionalInfo> additionalInfos;

}
