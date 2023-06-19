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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public CartDetailDto getCart() {
        return cart;
    }

    public void setCart(CartDetailDto cart) {
        this.cart = cart;
    }
}
