package com.example.demo.dto;

public class LoanRequestDTO {
    private Long resourceId;
    private Long userId;

    // Getters and setters

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
