package com.example.LabManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.service.UserService;
import com.example.LabManagementApplication.web.UserRequest;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method=RequestMethod.POST, value="/create-user")
    public ResponseEntity<Users> createUser(@RequestBody UserRequest userRequest) {
        System.out.println("Received JSON data: " + userRequest.toString());
        Users newUser = userService.createUser(
            userRequest.getFirstName(),
            userRequest.getLastName(),
            userRequest.getEmail(),
            userRequest.getRole(),
            userRequest.getPassword()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
