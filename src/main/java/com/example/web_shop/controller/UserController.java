package com.example.web_shop.controller;

import com.example.web_shop.entity.User;
import com.example.web_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPanel(Principal principal, Model model){

        User user = userService.findByUsername(principal.getName());

        if (user != null){
            model.addAttribute("user", user);
        }else {
            return "error/404";
        }
        return "user";
    }
}
