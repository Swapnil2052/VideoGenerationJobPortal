package com.example.VideoGenerationJobPortal.Model;

import java.time.Instant;

public class Job {
    private String id;
    private String productImageUrl;
    private String template;
    private JobStatus status;
    private Instant createdAt;

    public Job(String id, String productImageUrl, String template, JobStatus status, Instant createdAt) {
        this.id = id;
        this.productImageUrl = productImageUrl;
        this.template = template;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Job() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
