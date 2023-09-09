package com.example.LabManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.UserRepository;


@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = userRepository.findbyEmail(username);
        model.addAttribute("first_name", user.getFirstName());
        model.addAttribute("last_name", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("role", user.getRole());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/buttons")
    public String buttons() {
        return "buttons";
    }

    @GetMapping("/cards")
    public String cards() {
        return "cards";
    }
}
