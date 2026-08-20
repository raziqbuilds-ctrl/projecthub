package com.projecthub.backend.dto.application;

import jakarta.validation.constraints.Size;

public class ApplicationCreateRequest {

    @Size(max = 1000)
    private String message;

    public ApplicationCreateRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
