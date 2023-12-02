package com.example.web_shop.service;

import com.example.web_shop.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface ShoppingCartService {

    void addProduct(Product product);
    void removeProduct(Product product);
    void clearProduct();
    Map<Product, Integer> productInCart();
    BigDecimal totalPrice();
    void cartCheckout();
}
