package org.example.library_management_system.Service;

import org.example.library_management_system.DTO.AddUserRequest;
import org.example.library_management_system.Mapper.UserMapper;
import org.example.library_management_system.Model.User;
import org.example.library_management_system.Repository.UserRepository;
import org.example.library_management_system.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User adduser(AddUserRequest addUserRequest) {
        User user = UserMapper.mapToUser(addUserRequest);
        user.setUserType(UserType.Student);
        return userRepository.save(user);
    }

    public User fetchUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }
}
