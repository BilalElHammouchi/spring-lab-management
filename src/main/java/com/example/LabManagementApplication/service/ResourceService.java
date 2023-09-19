package com.example.LabManagementApplication.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LabManagementApplication.model.Resource;
import com.example.LabManagementApplication.repository.ResourceRepository;

@Service
public class ResourceService {
    
    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> getAllEntities() {
        return this.resourceRepository.findAll();
    }

    public Resource createResource(String name, String description, boolean availability) {
        Resource newResource = new Resource();
        newResource.setName(name);
        newResource.setDescription(description);
        newResource.setAvailability(availability);
        newResource = resourceRepository.save(newResource);
        return newResource;
    }

    public void updateResource(Resource resource) {
        Resource existingResource = resourceRepository.findById(resource.getId()).orElse(null);
        if (existingResource != null) {
            existingResource.setName(resource.getName());
            existingResource.setDescription(resource.getDescription());
            existingResource.setAvailability(resource.getAvailability());
            resourceRepository.save(existingResource);
        } else {
            throw new EntityNotFoundException("Resource with ID " + resource.getId() + " not found");
        }
    }
}
