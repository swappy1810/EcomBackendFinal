package com.example.roleBased.dto;

import lombok.*;

import javax.validation.constraints.AssertFalse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    private String message;
    private Boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
