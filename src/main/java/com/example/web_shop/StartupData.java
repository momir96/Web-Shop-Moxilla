package com.example.web_shop;

import com.example.web_shop.entity.Category;
import com.example.web_shop.entity.Product;
import com.example.web_shop.entity.User;
import com.example.web_shop.repository.CategoryRepository;
import com.example.web_shop.service.ProductService;
import com.example.web_shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StartupData implements CommandLineRunner{

    private final UserService userService;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(StartupData.class);

    @Autowired
    public StartupData(UserService userService, ProductService productService,
                       CategoryRepository categoryRepository) {
        this.userService = userService;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        adminAccount();
        userAccount();
        category();
        exampleProduct();
    }

    private void userAccount() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setConfirmPassword("user");
        user.setGender("Female");
        user.setEmail("user@example.com");
        userService.save(user);
    }

    private void adminAccount() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setConfirmPassword("admin");
        admin.setGender("Male");
        admin.setEmail("admin@gmail.com");
        userService.save(admin);
    }

    private void exampleProduct() {
        final String NAME = "Example Name";
        final String IMAGE_URL = "https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX7389458.jpg";
        final String DESCRIPTION = "Example Description";
        final BigDecimal PRICE = BigDecimal.valueOf(22);

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();

        product1.setName(NAME);
        product1.setImageUrl(IMAGE_URL);
        product1.setDescription(DESCRIPTION);
        product1.setCategory(categoryRepository.findByCategoryName("Adventure"));
        product1.setPrice(PRICE);

        product2.setName(NAME);
        product2.setImageUrl(IMAGE_URL);
        product2.setDescription(DESCRIPTION);
        product2.setCategory(categoryRepository.findByCategoryName("Adventure"));
        product2.setPrice(PRICE);

        product3.setName(NAME);
        product3.setImageUrl(IMAGE_URL);
        product3.setDescription(DESCRIPTION);
        product3.setCategory(categoryRepository.findByCategoryName("Novel"));
        product3.setPrice(PRICE);

        product4.setName(NAME);
        product4.setImageUrl(IMAGE_URL);
        product4.setDescription(DESCRIPTION);
        product4.setCategory(categoryRepository.findByCategoryName("Novel"));
        product4.setPrice(PRICE);

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
    }

    private void category() {
        Category category = new Category();
        category.setId(1);
        category.setCategoryName("Adventure");

        Category category1 = new Category();
        category1.setId(1);
        category1.setCategoryName("Novel");

        categoryRepository.save(category);
        categoryRepository.save(category1);
    }
}
