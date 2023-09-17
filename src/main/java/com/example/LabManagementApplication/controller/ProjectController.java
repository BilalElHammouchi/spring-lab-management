package com.example.LabManagementApplication.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.LabManagementApplication.model.Project;
import com.example.LabManagementApplication.repository.ProjectRepository;
import com.example.LabManagementApplication.service.ProjectService;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/addProject")
    public RedirectView addMember(@ModelAttribute("project") Project project) {
        System.out.println(project.getTitle());
        System.out.println(project.getDescription());
        System.out.println(project.getStartDate());
        System.out.println(project.getEndDate());
        System.out.println(project.getStatus());
        projectService.createProject(
            project.getTitle(),
            project.getDescription(),
            project.getStartDate(),
            project.getEndDate(),
            project.getStatus()
        );

        return new RedirectView("projectManagement");
    }
    
}
