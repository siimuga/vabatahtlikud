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
import java.util.Optional;

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

    public void updateUserData(UserRequest request, Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        String oldUsername = user.get().getUsername();
        Optional<Contact> contact = contactRepository.findById(user.get().getContact().getId());
        String oldEmail = contact.get().getEmail();

        user.get().setUsername(request.getUsername());
        contact.get().setFirstName(request.getFirstName());
        contact.get().setLastName(request.getLastName());
        contact.get().setSex(request.getSex());
        contact.get().setEmail(request.getEmail());
        contactRepository.save(contact.get());
        userRepository.save(user.get());

        if (contactRepository.countByEmail(request.getEmail()) > 1 && userRepository.countByUsername(request.getUsername()) > 1) {
            revertUserData(user, oldUsername, contact, oldEmail);
            ValidationService.validateEmailAndUsernameExists(contactRepository.existsByEmail(oldEmail), userRepository.existsByUsername(oldUsername));
        } else if (contactRepository.countByEmail(request.getEmail()) > 1) {
            revertUserData(user, oldUsername, contact, oldEmail);
            ValidationService.validateEmailExists(contactRepository.existsByEmail(oldEmail));
        } else if (userRepository.countByUsername(request.getUsername()) > 1) {
            revertUserData(user, oldUsername, contact, oldEmail);
            ValidationService.validateUsernameExists(userRepository.existsByUsername(oldUsername));
        }
    }

    private void revertUserData(Optional<User> user, String oldUsername, Optional<Contact> contact, String oldEmail) {
        contact.get().setEmail(oldEmail);
        user.get().setUsername(oldUsername);
        contactRepository.save(contact.get());
        userRepository.save(user.get());
    }

    private void validateRequest(String email, String username) {
        boolean existsByEmail = contactRepository.existsByEmail(email);
        boolean existsByUsername = userRepository.existsByUsername(username);
        ValidationService.validateUsernameExists(existsByUsername);
        ValidationService.validateEmailExists(existsByEmail);
    }


}
