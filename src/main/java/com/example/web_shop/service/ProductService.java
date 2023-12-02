package com.example.web_shop.service;

import com.example.web_shop.entity.Product;

import java.util.List;

public interface ProductService {

    void save(Product product);
    void edit(long id, Product newProduct);
    void delete(long id);
    Product findById(long id);
    List<Product> findAllByOrderByIdAsc();
    List<Product> findAllByCategoryId(long id);
    long count();
}
