package com.example.roleBased.dto;

import lombok.*;

/**
 * This class have required attributes and request body format for login and authentication module
 * @author krishna_rawat
 * @version v0.0.1
 */

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Login {

    private String email;
    private String password;
}