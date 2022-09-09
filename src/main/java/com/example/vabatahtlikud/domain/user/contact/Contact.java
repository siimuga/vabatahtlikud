package com.example.vabatahtlikud.domain.user.contact;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sex", length = 1)
    private String sex;

    @Column(name = "email")
    private String email;

}