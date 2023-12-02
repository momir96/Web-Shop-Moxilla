package com.example.web_shop.service.Impl;

import com.example.web_shop.entity.Category;
import com.example.web_shop.repository.CategoryRepository;
import com.example.web_shop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }
}
