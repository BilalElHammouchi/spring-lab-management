package com.example.LabManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LabManagementApplication.model.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
}