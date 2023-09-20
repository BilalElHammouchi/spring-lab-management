package com.example.LabManagementApplication.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.example.LabManagementApplication.model.Resource;
import com.example.LabManagementApplication.repository.ResourceRepository;
import com.example.LabManagementApplication.service.ResourceService;
import com.example.LabManagementApplication.web.FileUpload;

@RestController
public class ResourceController {
 
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceRepository resourceRepository;

    @PostMapping("/addResource")
    public RedirectView addResource(@ModelAttribute("resource") Resource resource, 
        @RequestParam("image") MultipartFile file) throws IOException{
        Resource newResource = resourceService.createResource(
            resource.getName(), 
            resource.getDescription(), 
            resource.getAvailability()
        );
        if(!file.isEmpty()){
            int lastIndex = file.getOriginalFilename().lastIndexOf(".");
            String extension = "."+file.getOriginalFilename().substring(lastIndex + 1);
            String fileName = "resource_"+String.valueOf(newResource.getId())+extension;
            FileUpload.saveFile(PublicationController.UPLOAD_DIRECTORY, fileName, file);
        }
        return new RedirectView("resourceManagement");
    }

    @PostMapping("/deleteResource")
    public RedirectView deleteResource(@RequestParam("resourceId") Long resourceId) {
        Optional<Resource> resourceToDelete = resourceRepository.findById(resourceId);

        if(resourceToDelete.isPresent()){
            resourceRepository.deleteById(resourceId);
        }

        return new RedirectView("resourceManagement");
    }

    @GetMapping("/getResourceById/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        Optional<Resource> resource = resourceRepository.findById(id);
        
        if (resource.isPresent()) {
            return new ResponseEntity<>(resource.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/editResource")
    public RedirectView editResource(@ModelAttribute("resource") Resource resource, 
        @RequestParam("image") MultipartFile file) {
        Resource resource_ = resourceRepository.getReferenceById(resource.getId());
        resource_.setName(resource.getName());
        resource_.setDescription(resource.getDescription());
        resource_.setAvailability(resource.getAvailability());
        resourceService.updateResource(resource_);
        return new RedirectView("resourceManagement");
    }
}
