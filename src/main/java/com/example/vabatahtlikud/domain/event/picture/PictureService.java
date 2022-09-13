package com.example.vabatahtlikud.domain.event.picture;

import com.example.vabatahtlikud.domain.event.event.EventRegister;
import com.example.vabatahtlikud.domain.event.event.EventRegisterRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class PictureService {

    @Resource
    private PictureDataRepository pictureDataRepository;

    @Resource
    private EventRegisterRepository eventRegisterRepository;

    public void addPicture(PictureDto pictureAsBase64) {
        PictureData picture = new PictureData();
        byte[] byteData = pictureAsBase64.getData().getBytes(StandardCharsets.UTF_8);
        picture.setData(byteData);
        Optional<EventRegister> eventRegister = eventRegisterRepository.findById(pictureAsBase64.getEventRegisterId());
        picture.setEventRegister(eventRegister.get());
        pictureDataRepository.save(picture);
    }
}
