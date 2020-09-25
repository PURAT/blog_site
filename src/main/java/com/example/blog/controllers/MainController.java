package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("about", "Узнайте много нового о нас!");
        return "about";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
