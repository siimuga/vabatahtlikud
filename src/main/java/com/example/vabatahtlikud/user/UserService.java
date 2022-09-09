package com.example.vabatahtlikud.user;

import com.example.vabatahtlikud.domain.user.contact.Contact;
import com.example.vabatahtlikud.domain.user.contact.ContactRepository;
import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.domain.user.user.UserRepository;
import com.example.vabatahtlikud.login.LoginRequest;
import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ContactRepository contactRepository;


    public User getValidUser(LoginRequest request) {
        boolean userExists = userRepository.existsBy(request.getUsername(), request.getPassword());
        User user = new User();
        Boolean userStatus = false;
        if (userExists) {
            user = userRepository.findUserBy(request.getUsername(), request.getPassword());
            userStatus = user.getStatus();
        }
        ValidationService.validatePasswordUserExists(userExists, userStatus);
        return user;
    }

    public UserResponse mapRequestAndAddUser(UserRequest request, Contact contact) {
        User user = userMapper.userRequestToUser(request);
        user.setContact(contact);
        userRepository.save(user);
        return userMapper.userToUserResponse(user);
    }

    public Contact getValidContact(UserRequest request) {
        boolean existsByEmail = contactRepository.existsByEmail(request.getEmail());
        boolean existsByUsername = userRepository.existsByUsername(request.getUsername());
        ValidationService.validateUsernameExists(existsByUsername);
        ValidationService.validateEmailExists(existsByEmail);
        return addContact(request);
    }

    public Contact addContact(UserRequest request) {
        Contact contact = userMapper.userRequestToContact(request);
        contactRepository.save(contact);
        return contact;
    }
}
