package com.example.demo.application.DTO;

import com.example.demo.application.Entity.ApplicationStatus;

public class UpdateApplicationStatusRequest {
    private ApplicationStatus status;

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(
            ApplicationStatus status) {

        this.status = status;
    }
}
