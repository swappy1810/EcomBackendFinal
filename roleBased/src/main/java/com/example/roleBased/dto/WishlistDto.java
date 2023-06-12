package com.example.roleBased.dto;

import java.util.Date;

public class WishlistDto {

    private int Listid;
    private ProductDto product;
    private UserDto userDto;
    private int quantity = 1;
    private  String username;
    private Date createdDate;

    public String getUsername() {
        return username;
    }


}
