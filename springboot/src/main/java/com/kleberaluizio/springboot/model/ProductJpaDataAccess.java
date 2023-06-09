package com.kleberaluizio.springboot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class ProductJpaDataAccess implements ProductDao {
    @Autowired
    ProductRepository productRepository;
}
