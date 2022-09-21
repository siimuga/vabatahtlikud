package com.example.vabatahtlikud.user;

import com.example.vabatahtlikud.domain.user.contact.Contact;
import com.example.vabatahtlikud.domain.user.user.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "role", source = "role.name")
    @Mapping(target = "contactId", source = "contact.id")
    UserInfo userToUserInfo(User user);

    Contact userRequestToContact(UserInfoInfo request);

    @Mapping(constant = "2", target = "role.id")
    @Mapping(constant = "true", target = "status")
    User userInfoInfoToUser(UserInfoInfo request);

    @Mapping(target = "userId", source = "id")
    UserResponse userToUserResponse(User user);


    @Mapping(source = "contact.firstName", target = "firstName")
    @Mapping(source = "contact.lastName", target = "lastName")
    @Mapping(source = "contact.sex", target = "sex")
    @Mapping(source = "contact.email", target = "email")
    UserInfoInfo userToUserInfoInfo(User user);
}
