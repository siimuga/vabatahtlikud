package com.example.vabatahtlikud.user;

import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.login.LoginRequest;
import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;


    public User getValidUser(LoginRequest request) {
        boolean userExists = userRepository.existsBy(request.getUsername(), request.getPassword());
        ValidationService.validatePasswordUserExists(userExists);
        return userRepository.findUserBy(request.getUsername(), request.getPassword());
    }
}
