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
    public Wishlist createWishList(Wishlist wishList, Integer userId, Integer productId) {
        Product product = this.productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id" + productId));
        User user = this.userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with this userId" + userId));
        // Wishlist wishList = this.dtoToWishList(wishListDto);
        wishList.setUsername(wishList.getUsername());
        wishList.setCreatedDate(new Date());
        wishList.setProduct(product);
        wishList.setUserId(userId);
        Wishlist SaveWishList = this.wishListDao.save(wishList);
        return SaveWishList;

    }

//    //dto to WishList fetch
//    private Wishlist dtoToWishList(WishlistDto wishListDto) {
//        return modelMapper.map(wishListDto,Wishlist.class);
//    }
//
//    //WISHlIST to dto fetch
//    private WishlistDto WishListToDto(Wishlist wishList) {
//        return modelMapper.map(wishList,WishlistDto.class);
//    }

    //ReadWishlist
    public List<Wishlist> readWishList(Integer userId) {
        User user = this.userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with this userId" + userId));
        return wishListDao.findByUserId(userId);
    }

    //delete wishlist of user by user Id
    @Transactional
    public void deleteWishList(Integer productId, Integer userId) {
        User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("product not found with this id" + productId));
        Product product = this.productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found with this id" + productId));
        List<Wishlist> wishlist2 = wishListDao.findByProduct(product);
        for (Wishlist wishlist1 : wishlist2) {
            if (userId == wishlist1.getUserId()) {
                wishListDao.deleteById(wishlist1.getListid());
            }
        }
    }
}
