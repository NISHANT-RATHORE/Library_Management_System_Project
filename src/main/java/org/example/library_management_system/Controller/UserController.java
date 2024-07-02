package org.example.library_management_system.Controller;

import jakarta.validation.Valid;
import org.example.library_management_system.DTO.AddUserRequest;
import org.example.library_management_system.Model.User;
import org.example.library_management_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/student")
    public ResponseEntity<User> addUser(@RequestBody @Valid AddUserRequest addUserRequest){
        User savedUser = userService.adduser(addUserRequest);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @PostMapping("/admin")
    public ResponseEntity<User> addUser(){
        return null;
    }

}
