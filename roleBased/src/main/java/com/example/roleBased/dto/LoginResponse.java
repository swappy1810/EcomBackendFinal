package com.example.roleBased.dto;

import com.example.roleBased.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginResponse {

    private String message;
    private Boolean status;
    private String email;
    private String roleName;
    private String jwtToken;
}