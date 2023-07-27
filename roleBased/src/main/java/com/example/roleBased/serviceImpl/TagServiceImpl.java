package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CategoryDao;
import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.TagDao;
import com.example.roleBased.entity.Category;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.Tags;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    public Tags createTags(Integer productId) {
        Product product = productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product id not found with this id" + productId));
        Category category = product.getCategory();
        int catId1 = product.getCategory().getCatId();
        double price = product.getProduct_price();
        Tags tags = new Tags();
        if ((price > 599 & price <= 1099) && catId1 == 1) {
            tags.setDiscountPercent(40);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 60 / 100);
        } else if ((price > 1099 && price <= 1599) && catId1 == 1) {
            tags.setDiscountPercent(60);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 40 / 100);
        }

        else if ((price > 599 & price <= 1099) && catId1 == 2) {
            tags.setDiscountPercent(40);
            tags.setProductPrice(product.getProduct_price());
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setTotalPrice(price * 60 / 100);
        } else if ((price > 1099 && price <= 1599) && catId1 == 2) {
            tags.setDiscountPercent(60);
            tags.setProductPrice(product.getProduct_price());
            tags.setCatId(product.getCategory().getCatId());
            tags.setTotalPrice(price * 40 / 100);
        }

        else if ((price > 599 & price <= 1099) && catId1 == 4) {
            tags.setDiscountPercent(40);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 60 / 100);
        } else if ((price > 1099 && price <= 1599) && catId1 == 4) {
            tags.setDiscountPercent(60);
            tags.setProductPrice(product.getProduct_price());
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            System.out.println(product);
            tags.setTotalPrice(price * 40 / 100);
        }

        else if ((price > 599 & price <= 1099) && catId1 == 6) {
            tags.setDiscountPercent(40);
            tags.setProductPrice(product.getProduct_price());
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setTotalPrice(price * 60 / 100);
        } else if ((price > 1099 && price <= 1599) && catId1 == 6) {
            tags.setDiscountPercent(60);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 40 / 100);
        }

        else if ((price > 599 & price <= 1099) && catId1 == 7) {
            tags.setDiscountPercent(40);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProduct(product);
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 60 / 100);
        } else if ((price > 1099 && price <= 1599) && catId1 == 7) {
            tags.setDiscountPercent(60);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 40 / 100);
        }

        else if (price <= 599 && (catId1==1 || catId1==2 || catId1 == 4|| catId1 == 6|| catId1 == 7)) {
            tags.setDiscountPercent(0);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 100 / 100);
        } else if (price > 1599 && (catId1==1 || catId1==2 || catId1 == 4|| catId1 == 6|| catId1 == 7)) {
            tags.setDiscountPercent(80);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 20 / 100);
        }

        else if ((price > 2099 & price <= 3099) && catId1 == 3) {
            tags.setDiscountPercent(40);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 60 / 100);
        } else if ((price > 3099 && price <= 4499) && catId1 == 3) {
            tags.setDiscountPercent(60);
            tags.setProductPrice(product.getProduct_price());
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setTotalPrice(price * 40 / 100);
        }

        else if ((price > 2100 & price <= 10999) && catId1 == 5) {
            tags.setDiscountPercent(40);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            System.out.println(product);
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 60 / 100);
        } else if ((price > 10999 && price <= 34999) && catId1 == 5) {
            tags.setDiscountPercent(60);
            tags.setProduct(product);
            System.out.println(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 40 / 100);
        }

        else if ((price > 3099 & price <= 10999) && catId1 == 8) {
            tags.setDiscountPercent(40);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 60 / 100);
        } else if ((price > 10999 && price <= 28599) && catId1 == 8) {
            tags.setDiscountPercent(60);
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 40 / 100);
        }
        else if (price <= 2099 && (catId1==3 || catId1==5 || catId1 == 8)) {
            tags.setDiscountPercent(0);
            tags.setProduct(product);
            System.out.println(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setProductPrice(product.getProduct_price());
            tags.setTotalPrice(price * 100 / 100);
        } else if (price >= 35000 && (catId1==3 || catId1==5 || catId1 == 8)) {
            tags.setDiscountPercent(80);
            System.out.println(product);
            tags.setProductPrice(product.getProduct_price());
            tags.setProduct(product);
            tags.setCatId(product.getCategory().getCatId());
            tags.setTotalPrice(price * 20 / 100);
        }
        else {
            return null;
        }
        tagDao.save(tags);
        return tags;
    }

     public List<Tags> getAllTags(){
         List<Tags> tags = this.tagDao.findAll();
         return tags.stream().map(tags1 -> (tags1)).collect(Collectors.toList());
     }

    public List<Tags> getProductByTags(Integer catId){
       Category category = categoryDao.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category npt found with this id"+catId));
       return tagDao.findByCatId(catId);
    }
}
