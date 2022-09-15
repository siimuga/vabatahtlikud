package com.example.vabatahtlikud.domain.event.additional_info;

import com.example.vabatahtlikud.domain.event.event.Event;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "additional_info")
public class AdditionalInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

}