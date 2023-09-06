package com.example.LabManagementApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/buttons")
    public String buttons() {
        return "buttons";
    }

    @GetMapping("/cards")
    public String cards() {
        return "cards";
    }
}
