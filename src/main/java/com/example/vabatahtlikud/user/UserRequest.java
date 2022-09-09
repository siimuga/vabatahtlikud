package com.example.vabatahtlikud.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
}
