package com.kleberaluizio.springboot.model;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public ProductModel addProduct(ProductModel productModel) {
        return productDao.insertProduct(productModel);
    }

    public List<ProductModel> getAllProducts() {
        return productDao.selectAllProducts();
    }

    public Object getProduct(UUID id) {
        Optional<ProductModel> product0 = productDao.selectProductById(id);

        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(product0.get());
    }

    public Object updateProduct(UUID id,
                                ProductRecordDto productRecordDto) {
        Optional<ProductModel> product0 = productDao.selectProductById(id);

        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = product0.get();

        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productDao.updateCustomer(productModel));
    }

    public Object deleteProductById(UUID id) {

        Optional<ProductModel> product0 = productDao.selectProductById(id);

        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = product0.get();
        productDao.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
