package com.projecthub.backend.dto.milestone;

import com.projecthub.backend.entity.Milestone;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MilestoneResponse {

    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private Milestone.MilestoneStatus status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;

    public MilestoneResponse() {
    }

    public MilestoneResponse(
            Long id,
            Long projectId,
            String title,
            String description,
            Milestone.MilestoneStatus status,
            LocalDate dueDate,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public Milestone.MilestoneStatus getStatus() {
        return status;
    }

    public void setStatus(Milestone.MilestoneStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
