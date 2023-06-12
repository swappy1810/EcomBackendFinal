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

    private String userName;
    private String password;
}
