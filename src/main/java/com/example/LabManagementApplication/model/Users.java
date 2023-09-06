package com.example.LabManagementApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String role;
    private String password;

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName(){
        return this.first_name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLastName() {
        return this.last_name;
    }
}