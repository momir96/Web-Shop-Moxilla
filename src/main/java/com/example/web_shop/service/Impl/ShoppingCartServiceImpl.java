package com.example.web_shop.service.Impl;

import com.example.web_shop.entity.Product;
import com.example.web_shop.service.ShoppingCartService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Map<Product, Integer> cart = new LinkedHashMap<>();

    @Override
    public void addProduct(Product product) {
        if (cart.containsKey(product)){
            cart.replace(product, cart.get(product) + 1);
        }else {
            cart.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (cart.containsKey(product)){
            if (cart.get(product) > 1)
                cart.replace(product, cart.get(product) - 1);
            else if (cart.get(product) == 1) {
                cart.remove(product);
            }
        }
    }

    @Override
    public void clearProduct() {
        cart.clear();
    }

    @Override
    public Map<Product, Integer> productInCart() {
        return Collections.unmodifiableMap(cart);
    }

    @Override
    public BigDecimal totalPrice() {
        return cart.entrySet().stream()
                .map(k-> k.getKey().getPrice().multiply(BigDecimal.valueOf(k.getValue())))
                .sorted()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void cartCheckout() {
        cart.clear();
    }
}
