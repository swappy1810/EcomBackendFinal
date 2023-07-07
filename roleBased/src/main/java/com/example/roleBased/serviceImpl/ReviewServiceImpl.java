package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.ReviewDao;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.dto.ReviewDto;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.Review;
import com.example.roleBased.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl {

    @Autowired
    private ReviewDao reviewDao ;

    public ReviewServiceImpl(ReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }
    public Review addReview(Review review, Product product){
        review.setProduct(product);
        return reviewDao.save(review);
    }
    public List<Review> getReviewByProduct(Product product){
        return reviewDao.findByProduct(product);
    }

}

