package com.example.LabManagementApplication.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Publication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false)
    private Users author;

    private Date publicationDate;

    @Column(length = 8192)
    private String description;

    private String header;

    public void setId(long id){
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    public void setAuthor(Users author) {
        this.author = author;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public Project getProject() {
        return this.project;
    }
    
    public Users getAuthor() {
        return this.author;
    }

    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHeader() {
        return this.header;
    }

    public String getPublicationDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.publicationDate);
    }
}
