package com.example.roleBased.dto;

import lombok.*;

/**
 * This class have required attributes and request body format for Authentication module
 * @author Tejas_Badjate
 * @version v0.0.1
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
