package com.example.LabManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LabManagementApplication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA will provide CRUD methods
}
