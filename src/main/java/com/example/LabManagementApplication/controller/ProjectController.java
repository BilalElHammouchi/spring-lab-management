package com.example.LabManagementApplication.controller;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.LabManagementApplication.model.Project;
import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.ProjectRepository;
import com.example.LabManagementApplication.service.ProjectService;
import com.example.LabManagementApplication.service.UserService;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @PostMapping("/addProject")
    public RedirectView addProject(@ModelAttribute("project") Project project, @RequestParam("selectedUsers") List<Long> selectedUsers, Model model) {
        try {
            System.out.println(project.getTitle());
            System.out.println(project.getDescription());
            System.out.println(project.getStartDate());
            System.out.println(project.getEndDate());
            System.out.println(project.getStatus());
            System.out.println(selectedUsers);
            Set<Users> users = new HashSet<>(userService.getUsersByIds(selectedUsers));
            projectService.createProject(
                project.getTitle(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus(),
                users
            );
        } catch (DataIntegrityViolationException e) {
            System.out.println(e);
        }
        

        return new RedirectView("projectManagement");
    }
    
}
