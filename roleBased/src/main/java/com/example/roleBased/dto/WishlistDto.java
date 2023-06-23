package com.example.roleBased.dto;

import java.util.Date;

public class WishlistDto {

    private int Listid;
    private ProductDto product;
    private UserDto userDto;
    private int quantity = 1;
    private String username;
    private Date createdDate;

    public String getUsername() {
        return username;
    }

    public int getListid() {
        return Listid;
    }

    public void setListid(int listid) {
        Listid = listid;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}