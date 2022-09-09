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

    Contact userRequestToContact(UserRequest request);

    @Mapping(constant = "2", target = "role.id")
    @Mapping(constant = "true", target = "status")
    User userRequestToUser(UserRequest request);

    @Mapping(target = "userId", source = "id")
    UserResponse userToUserResponse(User user);

}
