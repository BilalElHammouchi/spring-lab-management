package com.example.LabManagementApplication.repository;

import com.example.LabManagementApplication.model.Publication;

public class PublicationResponseDTO {
    private Publication publication;
    private Long projectId;
    private Long authorId;

    public void setPublication(Publication publication){
        this.publication = publication;
    }

    public void setProjectId(Long projectId){
        this.projectId = projectId;
    }

    public void setAuthorId(Long authorId){
        this.authorId = authorId;
    }

    public Publication getPublication(){
        return this.publication;
    }

    public Long getProjectId(){
        return this.projectId;
    }

    public Long getAuthorId(){
        return this.authorId;
    }
}
