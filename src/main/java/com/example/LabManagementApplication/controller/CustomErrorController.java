package com.example.LabManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class CustomErrorController implements ErrorController {
    
    private static final String PATH = "/error";

    @Autowired
    private UserRepository userRepository;

    private void addAttributes(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = userRepository.findbyEmail(username);
        model.addAttribute("first_name", user.getFirstName());
        model.addAttribute("last_name", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("role", user.getRole());
    }

    @RequestMapping(value = PATH)
    public String error(Model model) {
        addAttributes(model);
        return "404";
    }

}
