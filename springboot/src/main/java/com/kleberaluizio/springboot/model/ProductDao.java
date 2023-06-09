package com.kleberaluizio.springboot.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDao {
    ProductModel insertProduct(ProductModel productModel);

    List<ProductModel> selectAllProducts();

    Optional<ProductModel> selectProductById(UUID id);

    Object updateCustomer(ProductModel productModel);

    void deleteProductById(UUID id);
}
