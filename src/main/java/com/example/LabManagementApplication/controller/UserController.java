package com.example.LabManagementApplication.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public RedirectView deleteUser(@RequestParam("userId") Long userId) {
        Optional<Users> userToDelete = userRepository.findById(userId);

        if(userToDelete.isPresent()){
            userRepository.deleteById(userId);
        }

        return new RedirectView("membersManagement");
    }

    @PostMapping("/editMember")
    public RedirectView editUser(@ModelAttribute("user") Users user) {
        Users userToUpdate = userRepository.findById(user.getId()).get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setRole(user.getRole());
        userService.updateUser(userToUpdate);

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

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = userRepository.findById(id);
        
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
        }
    }

}
