package com.example.vabatahtlikud.admin;

import lombok.Data;

@Data
public class AdminUserRequest {
    private Integer seqNr;
    private Integer id;
    private String username;
    private String password;
    private String status;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
}
