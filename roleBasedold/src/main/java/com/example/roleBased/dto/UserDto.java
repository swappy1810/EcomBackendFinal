package com.example.roleBased.dto;

import com.example.roleBased.entity.Role;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int userId;
    private String username;
    private String email;
    @Size(min = 8,max = 16,message = "Invalid password!!(8-16 characters)")
    private String password;
    private Role role;
    private CartDetailDto cart;

}
