package com.example.web_shop.controller;

import com.example.web_shop.entity.Product;
import com.example.web_shop.service.ProductService;
import com.example.web_shop.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public CartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("products", shoppingCartService.productInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());

        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            shoppingCartService.addProduct(product);
            logger.debug(String.format("Product with id: %s added from shopping cart", id));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            shoppingCartService.removeProduct(product);
            logger.debug(String.format("Product with id: %s removed from shopping cart", id));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearProductInCart(){
        shoppingCartService.clearProduct();
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout(){
        shoppingCartService.cartCheckout();
        return "redirect:/cart";
    }
}





















