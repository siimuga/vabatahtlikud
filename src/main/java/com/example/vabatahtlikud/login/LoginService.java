package com.example.vabatahtlikud.login;

import com.example.vabatahtlikud.domain.user.contact.Contact;
import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.user.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public UserResponse registerNewUser(UserRequest request) {
        Contact contact = userService.addContact(request);
        return userService.mapRequestAndAddUser(request, contact);
    }
}
