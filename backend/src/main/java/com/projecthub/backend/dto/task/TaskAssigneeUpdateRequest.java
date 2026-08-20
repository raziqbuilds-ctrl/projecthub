package com.projecthub.backend.dto.task;

public class TaskAssigneeUpdateRequest {

    private Long userId;

    public TaskAssigneeUpdateRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
