package com.example.vabatahtlikud.user;

import com.example.vabatahtlikud.domain.user.contact.Contact;
import com.example.vabatahtlikud.domain.user.contact.ContactRepository;
import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.domain.user.user.UserRepository;
import com.example.vabatahtlikud.login.LoginRequest;
import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        validateRequest(request.getEmail(), request.getUsername());
        Contact contact = userMapper.userRequestToContact(request);
        contactRepository.save(contact);
        return contact;
    }

    @Transactional
    public void updateUserData(UserRequest request, Integer userId) {
        User user = userRepository.getReferenceById(userId);
        User userTemp = userRepository.getReferenceById(userId);
        Contact contact = contactRepository.getReferenceById(user.getContact().getId());
        Contact contactTemp = contactRepository.getReferenceById(user.getContact().getId());

        user.setUsername(request.getUsername());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setSex(request.getSex());
        contact.setEmail(request.getEmail());
        contactRepository.save(contact);
        userRepository.save(user);
        if (contactRepository.countByEmail(request.getEmail()) > 1 || userRepository.countByUsername(request.getUsername()) > 1) {
            contactRepository.save(contactTemp);
            userRepository.save(userTemp);
            validateRequest(request.getEmail(), request.getUsername());
        }

    }

    private void validateRequest(String email, String username) {
        boolean existsByEmail = contactRepository.existsByEmail(email);
        boolean existsByUsername = userRepository.existsByUsername(username);
        ValidationService.validateUsernameExists(existsByUsername);
        ValidationService.validateEmailExists(existsByEmail);
    }


}
