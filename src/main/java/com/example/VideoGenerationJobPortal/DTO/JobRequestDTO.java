package com.example.VideoGenerationJobPortal.DTO;

import jakarta.validation.constraints.NotBlank;

public class JobRequestDTO {
    @NotBlank
    private String productImageUrl;

    @NotBlank
    private String template;

    public JobRequestDTO() {
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
}
