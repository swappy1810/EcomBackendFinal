package com.example.roleBased.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JwtRequest {

    private String username;
    private String password;

}
