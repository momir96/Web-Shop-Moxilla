package com.example.web_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, String error){
        if(error != null)
            model.addAttribute("error",
                    "Invalid username or password");
        return "login";
    }
}
