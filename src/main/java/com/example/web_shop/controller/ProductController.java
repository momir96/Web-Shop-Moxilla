package com.example.web_shop.controller;

import com.example.web_shop.entity.Product;
import com.example.web_shop.service.CategoryService;
import com.example.web_shop.service.ProductService;
import com.example.web_shop.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final ProductValidator productValidator;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,
                             ProductValidator productValidator, CategoryService categoryService) {
        this.productService = productService;
        this.productValidator = productValidator;
        this.categoryService = categoryService;
    }

    @GetMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("productForm", new Product());
        model.addAttribute("method", "new");
        model.addAttribute("categories", categoryService.findAll());

        return "product";
    }

    @PostMapping("/product/new")
    public String newProduct(@ModelAttribute("productForm") Product productForm, BindingResult result, Model model){
        productValidator.validate(productForm, result);

        if (result.hasErrors()){
            logger.error(String.valueOf(result.getFieldError()));
            model.addAttribute("method", "new");
            return "product";
        }
        productService.save(productForm);
        logger.debug(String.format("Product with id: %s successfully created", productForm.getId()));

        return "redirect:/home";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model){
        Product product = productService.findById(id);
        if (product != null){
            model.addAttribute("productForm", product);
            model.addAttribute("method", "edit");
            return "product";
        }else {
            return "error/404";
        }
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") long id,
                              @ModelAttribute("productFrom") Product productForm,
                              BindingResult result, Model model){
        productValidator.validate(productForm, result);

        if (result.hasErrors()){
            logger.error(String.valueOf(result.getFieldError()));
            model.addAttribute("method", "edit");
            return "product";
        }
        productService.edit(id, productForm);
        logger.debug(String.format("Product with id: %s has been successfully edited.", id));

        return "redirect:/home";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            productService.delete(id);
            logger.debug(String.format("Product with id: %s successfully deleted.", product.getId()));
            return "redirect:/home";
        }else {
            return "error/404";
        }
    }
}






















