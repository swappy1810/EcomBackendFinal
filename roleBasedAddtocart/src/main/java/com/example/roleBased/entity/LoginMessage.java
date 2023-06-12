package com.example.roleBased.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginMessage {

    private String message;

    private boolean status;

    public LoginMessage(String s, boolean b) {
    }
}
