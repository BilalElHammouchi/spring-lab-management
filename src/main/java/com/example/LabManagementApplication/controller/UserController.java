package com.example.LabManagementApplication.controller;

import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.UserRepository;
import com.example.LabManagementApplication.service.UserService;
import com.example.LabManagementApplication.web.UserRequest;

@RestController
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method=RequestMethod.POST, value="/create-user")
    public UserRequest createUser(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        userService.createUser(
            userRequest.getFirstName(),
            userRequest.getLastName(),
            userRequest.getEmail(),
            userRequest.getRole(),
            userRequest.getPassword()
        );
        authWithHttpServletRequest(request, userRequest.getEmail(), userRequest.getPassword());
        return userRequest;
    }

    @PostMapping("/deleteMember")
    public RedirectView deleteUser(@RequestParam("userEmail") String userEmail) {
        Users userToDelete = userRepository.findbyEmail(userEmail);

        userRepository.delete(userToDelete);

        return new RedirectView("membersManagement");
    }

    @PostMapping("/addMember")
    public RedirectView addMember(@ModelAttribute("user") Users user) {
        userService.createUser(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getRole(),
            user.getPassword()
        );

        return new RedirectView("membersManagement");
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
        }
    }

}
