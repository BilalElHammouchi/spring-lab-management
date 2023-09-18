package com.example.LabManagementApplication.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @PostMapping("/deleteProject")
    public RedirectView deleteProject(@RequestParam("projectId") Long projectId) {
        Optional<Project> projectToDelete = projectRepository.findById(projectId);

        if(projectToDelete.isPresent()){
            projectRepository.deleteById(projectId);
        }

        return new RedirectView("projectManagement");
    }

    @GetMapping("/getProjectById/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        
        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PostMapping("/editProject")
    public RedirectView editProject(@ModelAttribute("project") Project project, @RequestParam(name="selectedUsersEdit", required=false) List<Long> selectedUsers) {
        Project project_ = projectRepository.getReferenceById(project.getId());
        project_.removeUsers();
        project_.setTitle(project.getTitle());
        project_.setDescription(project.getDescription());
        project_.setStartDate(project.getStartDate());
        project_.setEndDate(project.getEndDate());
        project_.setStatus(project.getStatus());
        if(selectedUsers != null) {
            Set<Users> users = new HashSet<>(userService.getUsersByIds(selectedUsers));
            for (Users user : users) {
                project_.getUsers().add(user);
            }
        }
        projectService.updateProject(project_);
        return new RedirectView("projectManagement");
    }
}
