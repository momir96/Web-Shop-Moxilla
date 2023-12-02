package com.example.web_shop.controller;

import com.example.web_shop.entity.Product;
import com.example.web_shop.service.CategoryService;
import com.example.web_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", "/index", "/home"})
    public String home(Model model){
        model.addAttribute("products", getAllProducts());
        model.addAttribute("productsCount", productCount());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    @RequestMapping("/searchByCategory")
    public String homePost(@RequestParam("categoryId") long id, Model model){
        model.addAttribute("books", productService.findAllByCategoryId(id));
        model.addAttribute("booksCount", productService.count());
        model.addAttribute("categories", categoryService.findAll());

        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    private List<Product> getAllProducts(){
        return productService.findAllByOrderByIdAsc();
    }

    private long productCount(){
        return productService.count();
    }
}























