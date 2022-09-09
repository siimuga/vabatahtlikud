package com.example.vabatahtlikud.domain.event.location.country;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "county")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;


}