package com.example.LabManagementApplication.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LabManagementApplication.model.Project;
import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Transactional
    public Project createProject(String title, String description, Date startDate, Date endDate, String status, Set<Users> users) {
        Project newProject = new Project();
        newProject.setTitle(title);
        newProject.setDescription(description);
        newProject.setStartDate(startDate);
        newProject.setEndDate(endDate);
        newProject.setStatus(status);
        newProject = projectRepository.save(newProject);
        System.out.println("Project ID after save: " + newProject.getId());
        System.out.println("Associated Users: " + newProject.getUsers());

        // Add users to the project
        for (Users user : users) {
            newProject.getUsers().add(user);
        }
        
        // Log the state of the project after adding users
        System.out.println("Project ID after adding users: " + newProject.getId());
        System.out.println("Associated Users after adding users: " + newProject.getUsers());
        return newProject;
    }

    @Transactional
    public void addUserToProject(Project project, Users user) {
        project.getUsers().add(user);
    }

    public List<Project> getAllEntities() {
        return this.projectRepository.findAll();
    }

    public void saveProject(Project project) {
        this.projectRepository.save(project);
    }
}
