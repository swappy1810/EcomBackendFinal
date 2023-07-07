package com.example.roleBased.controller;

import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.ReviewDao;
import com.example.roleBased.dto.ReviewDto;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.Review;
import com.example.roleBased.entity.User;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import com.example.roleBased.serviceImpl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product/{productId}/review")
public class ReviewController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ReviewDao reviewDao ;

    public ReviewController(ProductDao productDao, ReviewDao reviewDao){

        this.productDao = productDao;
        this.reviewDao = reviewDao;
    }
    @PostMapping
    public ResponseEntity<String> addReview(@PathVariable int productId,
                                            @RequestParam("comment") String comment,
                                            @RequestParam("image")MultipartFile image){
        //Create a new product
        Optional<Product> optionalProduct = productDao.findById(productId);
        if(optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Product product = optionalProduct.get();

        //create review
        Review review = new Review();
        review.setProduct(product);
        review.setComment(comment);

        //save the review
        reviewDao.save(review);

        //image save
        String filename = productId+ "_"+review.getId()+ ".jpg";

        try{
            File imageFile = new File(filename);
            image.transferTo(imageFile);
        }catch (IOException e){
            //Handle the exception
        }
        return ResponseEntity.ok("Review added successfully");
    }

}

