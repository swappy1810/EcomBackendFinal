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

import java.util.List;
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
    public ProductDto createProduct(ProductDto productDto, Integer subCatId) {
        SubCategory subCategory = this.subCategoryDao.findById(subCatId).orElseThrow(()->new ResourceNotFoundException("Category not found with this id"+subCatId));
        Product product = this.dtoToProduct(productDto);
        product.setCategory(subCategory.getCategory());
        product.setSubCategory(subCategory);
        product.setProduct_price(productDto.getProduct_price());
        product.setProduct_image(productDto.getProduct_image());
        product.setProduct_name(productDto.getProduct_name());
        product.setProduct_short_desc(productDto.getProduct_short_desc());
        product.setProduct_long_desc(productDto.getProduct_long_desc());
        product.setQuantity(productDto.getQuantity());
        product.setRating(productDto.getRating());
        Product savedProduct = this.productDao.save(product);
        return this.productToDto(savedProduct);
    }

    //update the product by product Id
    public ProductDto updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        Product product = this.productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id " + id));
        product.setProduct_name(productDto.getProduct_name());
        product.setProduct_short_desc(productDto.getProduct_short_desc());
        product.setProduct_long_desc(productDto.getProduct_long_desc());
        product.setProduct_price(productDto.getProduct_price());
        product.setProduct_image(productDto.getProduct_image());
        product.setQuantity(productDto.getQuantity());
        Product updateProduct = this.productDao.save(product);
        ProductDto productDto1 = this.productToDto(updateProduct);
        return productDto1;
    }

    //to get products by product id
    public ProductDto getProductById(Integer id) {
        Product product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id " + id));
        return this.productToDto(product);
    }

    //to get all products
    public List<Product> getAllProduct() {
        List<Product> products = this.productDao.findAll();
        return products.stream().map(product -> (product)).collect(Collectors.toList());
    }

    //to delete the product by product Id
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Integer id) {
        Product Product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id " + id));
        this.productDao.deleteById(id);
        return null;
    }

    //get list product by category with category id
    public List<ProductDto> findAllByCategory(Integer catId) {
        Category cat = categoryDao.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category with this id is not found"+catId));
        List<Product> findByCategory = this.productDao.findByCategory(cat);
        return findByCategory.stream().map(product -> productToDto(product)).collect(Collectors.toList());
    }

    //dto to product fetch
    private Product dtoToProduct(ProductDto productDto) {
        return modelMapper.map(productDto,Product.class);
    }

    //product to dto fetch
    public ProductDto productToDto(Product product) {
        return modelMapper.map(product,ProductDto.class);
    }

    //Search API of products
    public List<Product> searchProducts(String query) {
        return productDao.searchProducts(query);
    }
}
