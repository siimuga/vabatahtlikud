package com.example.vabatahtlikud.domain.event.additional_info;

import com.example.vabatahtlikud.domain.event.event.EventRegister;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_register_id", nullable = false)
    private EventRegister eventRegister;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

}