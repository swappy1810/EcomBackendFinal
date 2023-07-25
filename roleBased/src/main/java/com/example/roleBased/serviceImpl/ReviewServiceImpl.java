package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.ReviewDao;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.Review;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl {
    private final ReviewDao reviewDao;
    private final ProductDao productDao;

    public ReviewServiceImpl(ReviewDao reviewDao, ProductDao productDao){
        this.reviewDao = reviewDao;
        this.productDao = productDao;
    }

    public List<Review>  getReviewByProductId(int productId){
        Product product = productDao.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with this id :" + productId));
        return reviewDao.findByproduct(product);
    }
    public Review createReview(Review review){
        return reviewDao.save(review);
    }
}
