package com.example.VideoGenerationJobPortal.DTO;

import com.example.VideoGenerationJobPortal.Model.JobStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateJobStatusDTO {
    @NotNull
    private JobStatus status;

    public UpdateJobStatusDTO() {
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}
