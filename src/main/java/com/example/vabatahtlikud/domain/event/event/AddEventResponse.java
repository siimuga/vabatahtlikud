package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfoRequest;
import com.example.vabatahtlikud.domain.event.task.TaskDateInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddEventResponse implements Serializable {
    private Integer eventId;
    private List<TaskDateInfo> tasks;
    private List<AdditionalInfoRequest> additionalInfos;

}
