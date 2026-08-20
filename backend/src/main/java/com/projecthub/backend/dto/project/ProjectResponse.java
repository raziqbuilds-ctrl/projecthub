package com.projecthub.backend.dto.project;

import com.projecthub.backend.entity.Project;

import java.time.LocalDateTime;

public class ProjectResponse {

    private Long id;

    private Long ownerId;

    private String ownerName;

    private String title;

    private String description;

    private Project.ProjectType projectType;

    private Project.ProjectStatus status;

    private Integer maxTeamSize;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProjectResponse() {
    }

    public ProjectResponse(
            Long id,
            Long ownerId,
            String ownerName,
            String title,
            String description,
            Project.ProjectType projectType,
            Project.ProjectStatus status,
            Integer maxTeamSize,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.title = title;
        this.description = description;
        this.projectType = projectType;
        this.status = status;
        this.maxTeamSize = maxTeamSize;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project.ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(Project.ProjectType projectType) {
        this.projectType = projectType;
    }

    public Project.ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(Project.ProjectStatus status) {
        this.status = status;
    }

    public Integer getMaxTeamSize() {
        return maxTeamSize;
    }

    public void setMaxTeamSize(Integer maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
