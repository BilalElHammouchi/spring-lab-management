package com.example.LabManagementApplication.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.example.LabManagementApplication.model.Resource;
import com.example.LabManagementApplication.service.FileUploadService;
import com.example.LabManagementApplication.service.ResourceService;

@RestController
public class ResourceController {
 
    @Autowired
    private ResourceService resourceService;

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
            FileUploadService.saveFile(PublicationController.UPLOAD_DIRECTORY, fileName, file);
        }
        return new RedirectView("resourceManagement");
    }
}
