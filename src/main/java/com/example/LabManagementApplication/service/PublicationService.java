package com.example.LabManagementApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LabManagementApplication.model.Publication;
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
}
