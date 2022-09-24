package com.example.vabatahtlikud.user;

import com.example.vabatahtlikud.admin.AdminUserRequest;
import com.example.vabatahtlikud.domain.user.contact.Contact;
import com.example.vabatahtlikud.domain.user.contact.ContactRepository;
import com.example.vabatahtlikud.domain.user.user.User;
import com.example.vabatahtlikud.domain.user.user.UserRepository;
import com.example.vabatahtlikud.login.LoginRequest;
import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
        if (request.getUsername().isBlank() || request.getPassword().isBlank()) {
            ValidationService.validatePasswordUserExists(false, false);
        }
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

    public UserResponse mapRequestAndAddUser(UserInfoInfo request, Contact contact) {
        validateUserCreation(request.getUsername(), request.getPassword());
        User user = userMapper.userInfoInfoToUser(request);
        user.setContact(contact);
        userRepository.save(user);
        return userMapper.userToUserResponse(user);
    }

    private void validateUserCreation(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            ValidationService.validateUserCreation(username.isBlank(), password.isBlank());
        }
    }

    public Contact getValidContact(UserInfoInfo request) {
        validateRequest(request.getEmail(), request.getUsername());
        Contact contact = userMapper.userRequestToContact(request);
        contactRepository.save(contact);
        return contact;
    }

    public void updateUserData(UserInfoInfo request) {
        Optional<User> user = userRepository.findById(request.getUserId());
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


    public void deleteUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        Boolean status = user.get().getStatus();
        user.get().setStatus(!status);
        userRepository.save(user.get());
    }

    public UserInfoInfo findUserInfo(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return  userMapper.userToUserInfoInfo(user.get());

    }

    public List<AdminUserRequest> findAllUserInfos() {
        List<User> users = userRepository.findAllUsers(1);
        List<AdminUserRequest> adminUserRequests = userMapper.usersToAdminUserRequests(users);
        for (AdminUserRequest adminUserRequest : adminUserRequests) {
            adminUserRequest.setSeqNr(adminUserRequests.indexOf(adminUserRequest)+1);
            String username = adminUserRequest.getUsername();
            String password = adminUserRequest.getPassword();
            User user = userRepository.findUserBy(username, password);
            Boolean status = user.getStatus();
            if (status) {
                adminUserRequest.setStatus("aktiivne");
            } else {
                adminUserRequest.setStatus("kustutatud");
            }
        }
        return  adminUserRequests;
    }

    public void activateUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        Boolean status = user.get().getStatus();
        user.get().setStatus(!status);
        userRepository.save(user.get());
    }
}
