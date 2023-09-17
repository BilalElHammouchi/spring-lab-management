package com.example.LabManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LabManagementApplication.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}