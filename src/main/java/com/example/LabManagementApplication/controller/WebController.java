package com.example.LabManagementApplication.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.LabManagementApplication.model.Project;
import com.example.LabManagementApplication.model.Publication;
import com.example.LabManagementApplication.model.Resource;
import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.UserRepository;
import com.example.LabManagementApplication.service.ProjectService;
import com.example.LabManagementApplication.service.PublicationService;
import com.example.LabManagementApplication.service.ResourceService;
import com.example.LabManagementApplication.service.UserService;


@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private ResourceService resourceService;

    private void addAttributes(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = userRepository.findbyEmail(username);
        model.addAttribute("first_name", user.getFirstName());
        model.addAttribute("last_name", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("role", user.getRole());
        model.addAttribute("membersAmount", userService.getAllEntities().size());
        model.addAttribute("projectsAmount", projectService.getAllEntities().size());
        model.addAttribute("publicationsAmount", publicationService.getAllEntities().size());
        model.addAttribute("resourcesAmount", resourceService.getAllEntities().size());
    }

    @GetMapping("/index")
    public String index(Model model) {
        addAttributes(model);
        model.addAttribute("members", userService.getAllEntities());
        model.addAttribute("projects", projectService.getAllEntities());
        model.addAttribute("publications", publicationService.getAllEntities());
        model.addAttribute("resources", resourceService.getAllEntities());
        model.addAttribute("publicationProjectUser", getPublicationProjectUser());
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
            // Uncomment to hide admins from members table
            // if (!user.getRole().contains("admin")) {
                members.add(user);
            //}
        }
        model.addAttribute("members", members);
        model.addAttribute("user", new Users());
        return "membersManagement";
    }

    @GetMapping("/getAllData")
    @ResponseBody
    public Map<String, List<?>> getMembers() {
        Map<String, List<?>> result = new HashMap<>();
        result.put("members", userService.getAllEntities());
        result.put("projects", projectService.getAllEntities());
        result.put("publications", publicationService.getAllEntities());
        
        result.put("publicationProjectUser", getPublicationProjectUser());
        result.put("resources", resourceService.getAllEntities());
        return result;
    }

    public List<Map<String, Long>> getPublicationProjectUser(){
        List<Map<String, Long>> publicationProjectUser = new ArrayList<>();
        for (Publication publication : publicationService.getAllEntities()) {
            Map<String, Long> entry = new HashMap<>();
            entry.put("publicationId", publication.getId());
            entry.put("projectId", publication.getProject().getId());
            entry.put("authorId", publication.getAuthor().getId());
            publicationProjectUser.add(entry);
        }
        return publicationProjectUser;
    }

    @GetMapping("/projectManagement")
    public String projectManagement(Model model) {
        addAttributes(model);
        model.addAttribute("projects", projectService.getAllEntities());
        model.addAttribute("members", userService.getAllEntities());
        model.addAttribute("project", new Project());
        return "projectManagement";
    }

    @GetMapping("/publicationManagement")
    public String publicationManagement(Model model) {
        addAttributes(model);
        model.addAttribute("publications", publicationService.getAllEntities());
        model.addAttribute("members", new ArrayList<Users>());
        model.addAttribute("projects", projectService.getAllEntities());
        model.addAttribute("publication", new Publication());
        return "publicationManagement";
    }

    @GetMapping("/resourceManagement")
    public String resourceManagement(Model model) {
        addAttributes(model);
        model.addAttribute("resources", resourceService.getAllEntities());
        model.addAttribute("resource", new Resource());
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
}
