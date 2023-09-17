package com.example.LabManagementApplication.service;

import java.util.Date;
import java.util.List;

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

    public Project createProject(String title, String description, Date startDate, Date endDate, String status) {
        Project newProject = new Project();
        newProject.setTitle(title);
        newProject.setDescription(description);
        newProject.setStartDate(startDate);
        newProject.setEndDate(endDate);
        newProject.setStatus(status);

        return projectRepository.save(newProject);
    }

    @Transactional
    public void addUserToProject(Long projectId, Users user) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            project.getUsers().add(user);
        }
    }

    public List<Project> getAllEntities() {
        return this.projectRepository.findAll();
    }

    public void saveProject(Project project) {
        this.projectRepository.save(project);
    }
}
