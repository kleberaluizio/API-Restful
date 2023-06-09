package com.kleberaluizio.springboot.model;

import com.kleberaluizio.springboot.controllers.ProductController;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public ProductModel addProduct(ProductModel productModel) {
        return productDao.insertProduct(productModel);
    }

    public List<ProductModel> getAllProducts() {
        List<ProductModel> productsList = productDao.selectAllProducts();
        if(!productsList.isEmpty()){
            for (ProductModel product : productsList) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return productsList;
    }

    public Object getProduct(UUID id) {
        Optional<ProductModel> product0 = productDao.selectProductById(id);

        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        product0.get().add(linkTo(methodOn(ProductController.class).getProducts()).withRel("Products List"));
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
