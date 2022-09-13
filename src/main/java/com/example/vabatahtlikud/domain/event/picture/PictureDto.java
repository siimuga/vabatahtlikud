package com.example.vabatahtlikud.domain.event.picture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto {
    private String data;
    private Integer eventRegisterId;
}
