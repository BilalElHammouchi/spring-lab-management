package com.example.LabManagementApplication.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.UserRepository;
import com.example.LabManagementApplication.service.UserService;
import com.example.LabManagementApplication.web.UserRequest;


@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }


    private void addAttributes(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = userRepository.findbyEmail(username);
        model.addAttribute("first_name", user.getFirstName());
        model.addAttribute("last_name", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("role", user.getRole());
    }

    @GetMapping("/index")
    public String index(Model model) {
        addAttributes(model);
        return "index";
    }

    @GetMapping("/")
    public String homepage(Model model) {
        addAttributes(model);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/membersManagement")
    public String membersManagement(Model model) {
        addAttributes(model);
        List<Users> members = new ArrayList<Users>();
        for (Users user : userService.getAllEntities()) {
            if (!user.getRole().contains("admin")) {
                members.add(user);
            }
        }
        model.addAttribute("members", members);
        model.addAttribute("user", new Users());
        return "membersManagement";
    }

    @GetMapping("/projectManagement")
    public String projectManagement(Model model) {
        addAttributes(model);
        return "projectManagement";
    }

    @GetMapping("/publicationManagement")
    public String publicationManagement(Model model) {
        addAttributes(model);
        return "publicationManagement";
    }

    @GetMapping("/resourceManagement")
    public String resourceManagement(Model model) {
        addAttributes(model);
        return "resourceManagement";
    }

    @GetMapping("/roleManagement")
    public String roleManagement(Model model) {
        addAttributes(model);
        return "roleManagement";
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
