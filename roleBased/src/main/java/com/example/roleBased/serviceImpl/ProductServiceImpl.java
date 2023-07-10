package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CategoryDao;
import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.SubCategoryDao;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.Category;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.SubCategory;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl{
//autowired the required objects
    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Autowired
    private UserDao userDao;


    ModelMapper modelMapper = new ModelMapper();

    //add product to products
    public Product createProduct(Product product, Integer subCatId) {
        SubCategory subCategory = this.subCategoryDao.findById(subCatId).orElseThrow(()->new ResourceNotFoundException("Category not found with this id"+subCatId));
        product.setCategory(subCategory.getCategory());
        product.setSubCategory(subCategory);
        product.setProduct_price(product.getProduct_price());
        product.setProduct_image(product.getProduct_image());
        product.setProduct_name(product.getProduct_name());
        product.setProduct_short_desc(product.getProduct_short_desc());
        product.setProduct_long_desc(product.getProduct_long_desc());
        product.setQuantity(product.getQuantity());
        product.setRating(product.getRating());
        Product savedProduct = this.productDao.save(product);
        return savedProduct;
    }

    //update the product by product Id
    public Product updateProduct(@RequestBody Product productDto, @PathVariable Integer id) {
        Product product = this.productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id " + id));
        product.setProduct_name(productDto.getProduct_name());
        product.setProduct_short_desc(productDto.getProduct_short_desc());
        product.setProduct_long_desc(productDto.getProduct_long_desc());
        product.setProduct_price(productDto.getProduct_price());
        product.setProduct_image(productDto.getProduct_image());
        product.setQuantity(productDto.getQuantity());
        Product updateProduct = this.productDao.save(product);
        return updateProduct;
    }

    //to get products by product id
    public Product getProductById(Integer id) {
        Product product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id " + id));
        return product;
    }

    //to get all products
    public List<Product> getAllProduct() {
        List<Product> products = this.productDao.findAll();
        return products.stream().map(product -> (product)).collect(Collectors.toList());
    }

    //to delete the product by product Id
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        Product Product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id " + id));
        this.productDao.deleteById(id);
        return null;
    }

    //get list product by category with category id
    public List<Product> findAllByCategory(Integer catId) {
        Category cat = categoryDao.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category with this id is not found"+catId));
        List<Product> findByCategory = this.productDao.findByCategory(cat);
        return findByCategory.stream().map(product -> product).collect(Collectors.toList());
    }

    //dto to product fetch
//    private Product dtoToProduct(ProductDto productDto) {
//        return modelMapper.map(productDto,Product.class);
//    }
//
//    //product to dto fetch
//    public ProductDto productToDto(Product product) {
//        return modelMapper.map(product,ProductDto.class);
//    }

    //Search API of products
    public List<Product> searchProducts(String query) {
        return productDao.searchProducts(query);
    }

    /*public List<Product> findByRecomendations(String query) {
        List<Product> product = productDao.recommendation(query);
        for (Product product1 : product) {
            SubCategory subcategorynew = product1.getSubCategory();
            int subcatId = subcategorynew.getSubCatId();
            SubCategory subCategory1 = subCategoryDao.findById(subcatId).orElseThrow(() -> new ResourceNotFoundException("SubCategory with this id is not found" + subcatId));
            return productDao.findBySubCategory(subCategory1);
        }
        return null;
    }*/

    public List<Product> findByRecomendations(Integer productId) {
        Product product = productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id " + productId));
        SubCategory subcategorynew = product.getSubCategory();
            int subcatId = subcategorynew.getSubCatId();
            SubCategory subCategory1 = subCategoryDao.findById(subcatId).orElseThrow(() -> new ResourceNotFoundException("SubCategory with this id is not found" + subcatId));
            List<Product> similarProducts= new ArrayList<>();
            similarProducts= productDao.findBySubCategory(subCategory1);
            similarProducts.remove(product);
            return similarProducts;

    }

    // for review functionality
    public boolean doesProductExists(int productId){
        return productDao.existsById(productId);
    }

}
