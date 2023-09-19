package com.example.LabManagementApplication.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.example.LabManagementApplication.model.Project;
import com.example.LabManagementApplication.model.Publication;
import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.ProjectRepository;
import com.example.LabManagementApplication.repository.PublicationRepository;
import com.example.LabManagementApplication.repository.PublicationResponseDTO;
import com.example.LabManagementApplication.repository.UserRepository;
import com.example.LabManagementApplication.service.FileUploadService;
import com.example.LabManagementApplication.service.PublicationService;
import com.example.LabManagementApplication.service.UserService;

@RestController
public class PublicationController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired 
    private PublicationService publicationService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir")+"/src/main/resources/static/img";

    @GetMapping("/getUsersByProjectId/{id}")
    public ResponseEntity<Set<Users>> getUsersByProjectId(@PathVariable Long id, Model model) {
        Optional<Project> project = projectRepository.findById(id);
        
        if (project.isPresent()) {
            Project foundProject = project.get();
            return new ResponseEntity<>(foundProject.getUsers(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addPublication")
    public RedirectView addPublication(@ModelAttribute("publication") Publication publication, 
        @RequestParam("image") MultipartFile file, @RequestParam("projectId") Long projectId, 
        @RequestParam("authorId") Long authorId) throws IOException{
        Project project = projectRepository.findById(projectId).get();
        Users author = userRepository.findById(authorId).get();
        Publication newPublication = publicationService.createPublication(
            publication.getTitle(), 
            publication.getDescription(), 
            publication.getHeader(), 
            publication.getPublicationDate(), 
            project, 
            author
        );
        if(!file.isEmpty()){
            int lastIndex = file.getOriginalFilename().lastIndexOf(".");
            String extension = "."+file.getOriginalFilename().substring(lastIndex + 1);
            String fileName = "publication_"+String.valueOf(newPublication.getId())+extension;
            FileUploadService.saveFile(UPLOAD_DIRECTORY, fileName, file);
        }
        return new RedirectView("publicationManagement");
    }

    @PostMapping("/deletePublication")
    public RedirectView deletePublication(@RequestParam("publicationId") Long publicationId) {
        Optional<Publication> publicationToDelete = publicationRepository.findById(publicationId);

        if(publicationToDelete.isPresent()){
            publicationRepository.deleteById(publicationId);
        }

        return new RedirectView("publicationManagement");
    }

    @GetMapping("/getPublicationById/{id}")
    public ResponseEntity<PublicationResponseDTO> getPublicationById(@PathVariable Long id) {
        Optional<Publication> publication = publicationRepository.findById(id);
        
        if (publication.isPresent()) {
            PublicationResponseDTO responseDTO = new PublicationResponseDTO();
            responseDTO.setPublication(publication.get());
            responseDTO.setProjectId(publication.get().getProject().getId());
            responseDTO.setAuthorId(publication.get().getAuthor().getId());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/editPublication")
    public RedirectView editPublication(@ModelAttribute("publication") Publication publication, 
        @RequestParam("image") MultipartFile file, @RequestParam("projectIdEdit") Long projectId, 
        @RequestParam("authorIdEdit") Long authorId) {
        Publication publication_ = publicationRepository.getReferenceById(publication.getId());
        publication_.setTitle(publication.getTitle());
        publication_.setHeader(publication.getHeader());
        publication_.setDescription(publication.getDescription());
        publication_.setPublicationDate(publication.getPublicationDate());
        publication_.setProject(projectRepository.getReferenceById(projectId));
        publication_.setAuthor(userRepository.getReferenceById(authorId));
        publicationService.updatePublication(publication_);
        return new RedirectView("publicationManagement");
    }
}
