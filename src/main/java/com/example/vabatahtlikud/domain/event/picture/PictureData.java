package com.example.vabatahtlikud.domain.event.picture;

import com.example.vabatahtlikud.domain.event.event.EventRegister;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "picture_data")
public class PictureData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "data")
    private byte[] data;

}