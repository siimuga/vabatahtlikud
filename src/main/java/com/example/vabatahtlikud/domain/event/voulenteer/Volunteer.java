package com.example.vabatahtlikud.domain.event.voulenteer;

import com.example.vabatahtlikud.domain.event.event.Event;
import com.example.vabatahtlikud.domain.user.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "volunteers_size", nullable = false)
    private Integer volunteersSize;

    @Column(name = "info", length = 1000)
    private String info;

}