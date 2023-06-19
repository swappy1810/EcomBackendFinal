package com.example.roleBased.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginResponse {

    private String message;
    private Boolean status;
    private String userName;
    private String email;
    private String jwtToken;
}