package com.example.LabManagementApplication.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LabManagementApplication.model.Users;
import com.example.LabManagementApplication.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void insertAdminIfEmpty() {
        if (userRepository.count() == 0) {
            createUser("admin", "admin", "admin", "admin", "admin");
        }
    }

    public Users createUser(String firstName, String lastName, String email, String role, String password) {
        Users newUser = new Users();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setRole(role);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);

        return userRepository.save(newUser);
    }

    public void updateUser(Users user) {

        Users existingUser = userRepository.findById(user.getId()).orElse(null);

        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            userRepository.save(existingUser);
        } else {
            throw new EntityNotFoundException("User with ID " + user.getId() + " not found");
        }
    }

    public List<Users> getAllEntities() {
        return this.userRepository.findAll();
    }

    public List<Users> getUsersByIds(List<Long> userIds) {
        return userRepository.findAllById(userIds);
    }

}
