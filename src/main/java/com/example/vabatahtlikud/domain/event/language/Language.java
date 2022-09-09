package com.example.vabatahtlikud.domain.event.language;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

}