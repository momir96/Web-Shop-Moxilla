package com.example.web_shop.service.Impl;

import com.example.web_shop.entity.Product;
import com.example.web_shop.repository.ProductRepository;
import com.example.web_shop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void edit(long id, Product newProduct) {
        Product found = repository.getOne(id);
        found.setName(newProduct.getName());
        found.setImageUrl(newProduct.getImage_url());
        found.setDescription(newProduct.getDescription());
        found.setPrice(newProduct.getPrice());
        save(newProduct);
    }

    @Override
    public void delete(long id) {
        repository.delete(findById(id));
    }

    @Override
    public Product findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> findAllByOrderByIdAsc() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Product> findAllByCategoryId(long id) {
        return repository.findAllByCategoryId(id);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
