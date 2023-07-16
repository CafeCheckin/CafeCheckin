package com.example.CafeTour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("kakao")
    public String home(){
        return "kakao";
    }

    @GetMapping("home")
    public String h1(){
        return "home";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }
}
