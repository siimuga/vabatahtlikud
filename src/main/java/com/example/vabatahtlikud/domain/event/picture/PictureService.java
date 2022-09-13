package com.example.vabatahtlikud.domain.event.picture;

import com.example.vabatahtlikud.domain.event.event.Event;
import com.example.vabatahtlikud.domain.event.event.EventRegister;
import com.example.vabatahtlikud.domain.event.event.EventRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class PictureService {

    @Resource
    private PictureDataRepository pictureDataRepository;

    @Resource
    private EventRepository eventRepository;

    public void addPicture(PictureDto pictureAsBase64) {
        PictureData picture = new PictureData();
        byte[] byteData = pictureAsBase64.getData().getBytes(StandardCharsets.UTF_8);
        picture.setData(byteData);
        pictureDataRepository.save(picture);
    }
}
