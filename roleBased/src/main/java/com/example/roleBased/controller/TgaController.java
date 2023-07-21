package com.example.roleBased.controller;

import com.example.roleBased.entity.Tags;
import com.example.roleBased.serviceImpl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TgaController {

    @Autowired
    private TagServiceImpl tagService;

    @PostMapping("/tags/{productId}")
    public Tags createNewTags(@PathVariable Integer productId){
        return tagService.createTags(productId);
    }

}
