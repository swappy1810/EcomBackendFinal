package com.example.roleBased.entity;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

    private Set<Role> roles;
    private String message;
    private Boolean status;
    private int userId;
    private String jwtToken;
    private String email;

    public JwtResponse(User user, String newGeneratedToken) {
    }
}
