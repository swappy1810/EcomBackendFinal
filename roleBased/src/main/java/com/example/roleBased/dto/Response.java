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

}
