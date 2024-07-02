package org.example.library_management_system.Mapper;

import lombok.experimental.UtilityClass;
import org.example.library_management_system.DTO.AddUserRequest;
import org.example.library_management_system.Model.User;
import org.example.library_management_system.enums.UserStatus;

@UtilityClass
public class UserMapper {
    public User mapToUser(AddUserRequest userRequest){
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .phoneNo(userRequest.getPhoneNo())
                .address(userRequest.getAddress())
                .userStatus(UserStatus.Active)
                .build();
    }
}
