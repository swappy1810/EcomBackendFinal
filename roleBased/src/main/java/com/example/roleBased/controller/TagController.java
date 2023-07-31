package com.example.roleBased.controller;

import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.Tags;
import com.example.roleBased.serviceImpl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;

@RestController
@CrossOrigin
public class TagController {

    @Autowired
    private TagServiceImpl tagService;

    @PostMapping("/tags/{productId}")
    public Tags createNewTags(@PathVariable Integer productId){
        return tagService.createTags(productId);
    }

    //get all Tags list
    @GetMapping("/tags")
    public ResponseEntity<List<Tags>> getAllProducts(){
        return ResponseEntity.ok(this.tagService.getAllTags());
    }


    @GetMapping("/tags/{catId}")
    public ResponseEntity<List<Tags>> getByCategory(@PathVariable Integer catId){
        List<Tags> tags = tagService.getProductByTags(catId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

}
