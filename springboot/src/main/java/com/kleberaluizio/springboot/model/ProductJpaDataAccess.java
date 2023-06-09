package com.kleberaluizio.springboot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("jpa")
public class ProductJpaDataAccess implements ProductDao {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductModel insertProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    @Override
    public List<ProductModel> selectAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductModel> selectProductById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public Object updateCustomer(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    @Override
    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);
    }
}
