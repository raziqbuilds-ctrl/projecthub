package com.projecthub.backend.dto.application;

import com.projecthub.backend.entity.Application;
import jakarta.validation.constraints.NotNull;

public class ApplicationStatusUpdateRequest {

    @NotNull
    private Application.ApplicationStatus status;

    public ApplicationStatusUpdateRequest() {
    }

    public Application.ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(Application.ApplicationStatus status) {
        this.status = status;
    }
}
