package com.example.web_shop.service;

import com.example.web_shop.entity.Category;

import java.util.List;

public interface CategoryService {

    void save(Category category);
    List<Category> findAll();

}
