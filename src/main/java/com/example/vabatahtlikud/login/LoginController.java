package com.example.vabatahtlikud.login;

import com.example.vabatahtlikud.user.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

@PostMapping("/log-in")
@Operation(summary = "Sisse logimine")
    public UserInfo logIn(@RequestBody LoginRequest request) {
    return loginService.logIn(request);
}






}
