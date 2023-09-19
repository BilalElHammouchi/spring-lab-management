package com.example.LabManagementApplication.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LabManagementApplication.model.Project;
import com.example.LabManagementApplication.model.Publication;
import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.PublicationRepository;

@Service
public class PublicationService {
    
    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public List<Publication> getAllEntities() {
        return this.publicationRepository.findAll();
    }

    public Publication createPublication(String title, String description, String header, Date publicationDate, Project project, Users author) {
        Publication newPublication = new Publication();
        newPublication.setTitle(title);
        newPublication.setDescription(description);
        newPublication.setHeader(header);
        newPublication.setPublicationDate(publicationDate);
        newPublication.setProject(project);
        newPublication.setAuthor(author);
        newPublication = publicationRepository.save(newPublication);
        return newPublication;
    }

    public void updatePublication(Publication publication) {
        Publication existingPublication = publicationRepository.findById(publication.getId()).orElse(null);
        if (existingPublication != null) {
            existingPublication.setTitle(publication.getTitle());
            existingPublication.setHeader(publication.getHeader());
            existingPublication.setDescription(publication.getDescription());;
            existingPublication.setPublicationDate(publication.getPublicationDate());
            existingPublication.setAuthor(publication.getAuthor());
            existingPublication.setProject(publication.getProject());
            publicationRepository.save(existingPublication);
        } else {
            throw new EntityNotFoundException("Publication with ID " + publication.getId() + " not found");
        }
    }
}
