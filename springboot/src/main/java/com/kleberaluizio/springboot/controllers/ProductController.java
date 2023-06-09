package com.kleberaluizio.springboot.controllers;

import com.kleberaluizio.springboot.model.ProductRecordDto;
import com.kleberaluizio.springboot.model.ProductModel;
import com.kleberaluizio.springboot.model.ProductRepository;
import com.kleberaluizio.springboot.model.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController // Bean do Spring que tem API Rest
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(id));
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id")UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productRecordDto));
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProductById(id));
    }

//    @DeleteMapping("products/{id}")
//    public ResponseEntity<Object> deleteOneProduct(@PathVariable(value="id")UUID id){
//        Optional<ProductModel> product0 = productRepository.findById(id);
//
//        if(product0.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//        }
//        var productModel = product0.get();
//        productRepository.delete(productModel);
//        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
//    }

}
