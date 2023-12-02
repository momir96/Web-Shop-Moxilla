package com.example.web_shop.controller;

import com.example.web_shop.entity.User;
import com.example.web_shop.service.UserService;
import com.example.web_shop.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger
            (RegisterController.class);
    private final UserService userService;
    private final UserValidator userValidator;


    public RegisterController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    @GetMapping("/register")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult result){

        userValidator.validate(userForm, result);

        if (result.hasErrors()){
            logger.error(String.valueOf(result.getFieldError()));
            return "register";
        }
        userService.save(userForm);
        userService.login(userForm.getUsername(), userForm.getPassword());

        return "redirect:/home";
    }

}


























