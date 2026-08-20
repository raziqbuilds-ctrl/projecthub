package com.projecthub.backend.dto.task;

import com.projecthub.backend.entity.Task;
import jakarta.validation.constraints.NotNull;

public class TaskStatusUpdateRequest {

    @NotNull
    private Task.TaskStatus status;

    public TaskStatusUpdateRequest() {
    }

    public Task.TaskStatus getStatus() {
        return status;
    }

    public void setStatus(Task.TaskStatus status) {
        this.status = status;
    }
}
