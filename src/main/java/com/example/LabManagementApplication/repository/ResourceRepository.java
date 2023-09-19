package com.example.LabManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LabManagementApplication.model.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}