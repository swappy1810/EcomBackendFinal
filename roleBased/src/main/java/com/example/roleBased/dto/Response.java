package com.example.roleBased.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertFalse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String message;
    private Boolean status;

}
