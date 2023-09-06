package com.example.LabManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
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
    public RedirectView createUser(@RequestBody UserRequest userRequest) {
        System.out.println("Received JSON data: " + userRequest.toString());
        userService.createUser(
            userRequest.getFirstName(),
            userRequest.getLastName(),
            userRequest.getEmail(),
            userRequest.getRole(),
            userRequest.getPassword()
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            userRequest.getEmail(),
            userRequest.getPassword()
        );

        return new RedirectView("/index");
    }
}
