package com.example.roleBased.controller;

import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.ReviewDao;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.Review;
import com.example.roleBased.exception.ResourceNotFoundException;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import com.example.roleBased.serviceImpl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {
    private final ReviewServiceImpl reviewService;
    private final ProductServiceImpl productService;

    public ReviewController(ReviewServiceImpl reviewService, ProductServiceImpl productService){
        this.productService = productService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> getReview(@PathVariable int productId){

        if(!productService.doesProductExists(productId)){
            throw new ResourceNotFoundException("Product not found");
        }
        return reviewService.getReviewByProductId(productId);
    }

    @PostMapping
    public Review createReview(@PathVariable int productId,
                               @RequestBody Review review){

        if(!productService.doesProductExists(productId)){
            throw new ResourceNotFoundException("Product not found");
        }

        Product product = productService.getProductById(productId);
        review.setProduct(product);

        return reviewService.createReview(review);
    }
}