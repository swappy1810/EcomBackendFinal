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
@PreAuthorize("hasRole('Admin')")
    @PostMapping("/save/{catId}")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Integer catId){
        ProductDto createProductDto = this.productService.createProduct(productDto,catId);
        return new ResponseEntity<>(createProductDto, HttpStatus.CREATED);
    }
//update products from products list
@PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Integer id){
        ProductDto updatedProduct = this.productService.updateProduct(productDto,id);
        updatedProduct.setProduct_name(productDto.getProduct_name());
        updatedProduct.setProduct_short_desc(productDto.getProduct_long_desc());
        updatedProduct.setProduct_price(productDto.getProduct_price());
        updatedProduct.setProduct_image(productDto.getProduct_image());
        updatedProduct.setQuantity(productDto.getQuantity());
        return ResponseEntity.ok(updatedProduct);
    }
//delete the product by product id
@PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id){
        this.productService.deleteProduct(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Product Deleted Successfully",true), HttpStatus.OK);
    }
//get all products list
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(this.productService.getAllProduct());
    }
//get product by product id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductBYId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.productService.getProductById(id));
    }

    //get product by category using category id
    @GetMapping("/category/{catId}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable Integer catId){
        List<ProductDto> productDtos = this.productService.findAllByCategory(catId);
        return new ResponseEntity<List<ProductDto>>(productDtos,HttpStatus.ACCEPTED);
    }

    //Search API
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query){
        return ResponseEntity.ok(productService.searchProducts(query));
    }

}
