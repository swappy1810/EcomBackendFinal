package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CategoryDao;
import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.dao.WishListDao;
import com.example.roleBased.dto.WishListDto;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.User;
import com.example.roleBased.entity.WishList;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WishListServiceImpl {
    @Autowired
    private WishListDao wishListDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    ModelMapper modelMapper = new ModelMapper();
    //add product to WishList
    public WishListDto createWishList(WishListDto wishListDto, Integer userId,Integer productId) {
        Product product= this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found with this id"+productId));
        User user=this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with this userId"+userId));
        WishList wishList = this.dtoToWishList(wishListDto);
        wishList.setUsername(wishListDto.getUsername());
        wishList.setCreatedDate(new Date());
        wishList.setProduct(product);
        wishList.setUser(user);
        WishList SaveWishList = this.wishListDao.save(wishList);
        return this.WishListToDto(SaveWishList);

    }

    //dto to WishList fetch
    private WishList dtoToWishList(WishListDto wishListDto) {
        WishList wishList = modelMapper.map(wishListDto,WishList.class);
        return wishList;
    }

    //WISHlIST to dto fetch
    private WishListDto WishListToDto(WishList wishList) {
        WishListDto wishListDto = modelMapper.map(wishList,WishListDto.class);
        return  wishListDto;
    }

    //ReadWishlist
    public List<WishList> readWishList(Integer userId) {
        User user=this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with this userId"+userId));
        return wishListDao.findByUser(user);
    }

}

