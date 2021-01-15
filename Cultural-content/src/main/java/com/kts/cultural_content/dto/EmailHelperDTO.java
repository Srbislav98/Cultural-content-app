package com.kts.cultural_content.dto;

public class EmailHelperDTO {
    public String email;
    public EmailHelperDTO() {
    }
    public EmailHelperDTO(String password) {
        this.email=password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String password) {
        this.email = password;
    }
}
