package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CategoryDao;
import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.dao.WishListDao;
import com.example.roleBased.dto.WishlistDto;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.User;
import com.example.roleBased.entity.Wishlist;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public WishlistDto createWishList(WishlistDto wishListDto, Integer userId, Integer productId) {
        Product product= this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found with this id"+productId));
        User user=this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with this userId"+userId));
        Wishlist wishList = this.dtoToWishList(wishListDto);
        wishList.setUsername(wishListDto.getUsername());
        wishList.setCreatedDate(new Date());
        wishList.setProduct(product);
        wishList.setUser(user);
        Wishlist SaveWishList = this.wishListDao.save(wishList);
        return this.WishListToDto(SaveWishList);

    }

    //dto to WishList fetch
    private Wishlist dtoToWishList(WishlistDto wishListDto) {
        return modelMapper.map(wishListDto,Wishlist.class);
    }

    //WISHlIST to dto fetch
    private WishlistDto WishListToDto(Wishlist wishList) {
        return modelMapper.map(wishList,WishlistDto.class);
    }

    //ReadWishlist
    public List<Wishlist> readWishList(Integer userId) {
        User user=this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with this userId"+userId));
        return wishListDao.findByUser(user);
    }
    //delete wishlist of user by user Id
    @Transactional
    public void deleteWishList(Integer productId){
        Product product =  this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("product not found with this id" +productId));
        wishListDao.deleteByProduct(product);
    }
}
