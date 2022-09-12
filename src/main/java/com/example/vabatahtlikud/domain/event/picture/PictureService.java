package com.example.vabatahtlikud.domain.event.picture;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Service
public class PictureService {

    @Resource
    private PictureDataRepository pictureDataRepository;

    public void addPicture(PictureDto pictureAsBase64) {
        PictureData picture = new PictureData();
        byte[] byteData = pictureAsBase64.getData().getBytes(StandardCharsets.UTF_8);
        picture.setData(byteData);
        pictureDataRepository.save(picture);
    }
}
