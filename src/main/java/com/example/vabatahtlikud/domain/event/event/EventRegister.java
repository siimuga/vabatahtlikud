package com.example.vabatahtlikud.domain.event.event;

import lombok.Data;

import javax.persistence.*;



@Data
@Entity
@Table(name = "event_register")
public class EventRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

}