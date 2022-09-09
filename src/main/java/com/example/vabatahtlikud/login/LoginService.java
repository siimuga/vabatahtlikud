package com.example.vabatahtlikud.login;

import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.user.UserInfo;
import com.example.vabatahtlikud.user.UserMapper;
import com.example.vabatahtlikud.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginService {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    public UserInfo logIn(LoginRequest request) {
        User user = userService.getValidUser(request);
        return userMapper.userToUserInfo(user);
    }

}
