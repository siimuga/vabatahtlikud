package com.example.vabatahtlikud.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private Integer userId;
    private String role;
    private Integer contactId;
    private Boolean status;
}
