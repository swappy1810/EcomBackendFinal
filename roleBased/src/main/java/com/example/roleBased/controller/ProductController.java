package com.example.roleBased.controller;

import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.Product;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

//save or add the product
    @PostMapping("/save/{subCatId}")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, @PathVariable Integer subCatId){
        Product createProduct = this.productService.createProduct(product,subCatId);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }
//update products from products list
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable Integer id){
        Product updatedProduct = this.productService.updateProduct(product,id);
        updatedProduct.setProduct_name(product.getProduct_name());
        updatedProduct.setProduct_short_desc(product.getProduct_long_desc());
        updatedProduct.setProduct_price(product.getProduct_price());
        updatedProduct.setProduct_image(product.getProduct_image());
        updatedProduct.setQuantity(product.getQuantity());
        return ResponseEntity.ok(updatedProduct);
    }
//delete the product by product id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id){
        this.productService.deleteProduct(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Product Deleted Successfully",true), HttpStatus.OK);
    }
//get all products list
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(this.productService.getAllProduct());
    }
//get product by product id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductBYId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.productService.getProductById(id));
    }

    //get product by category using category id
    @GetMapping("/category/{catId}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Integer catId){
        List<Product> productDtos = this.productService.findAllByCategory(catId);
        return new ResponseEntity<List<Product>>(productDtos,HttpStatus.ACCEPTED);
    }

    //Search API
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query){
        return ResponseEntity.ok(productService.searchProducts(query));
    }

    //Recomendations API
    @GetMapping("/recommend/{productId}")
    public ResponseEntity<List<Product>> recommendations(@PathVariable Integer productId){
        return ResponseEntity.ok(productService.findByRecomendations(productId));
    }

}
