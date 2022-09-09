package com.example.vabatahtlikud.login;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginRequest  implements Serializable {
    @NotNull
    private String username;
    @NotNull
    private String password;

}
