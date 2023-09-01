package com.example.LabManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LabManagementApplication.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    
}
