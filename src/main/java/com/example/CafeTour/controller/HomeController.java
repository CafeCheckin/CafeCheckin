package com.example.CafeTour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @RequestMapping("/kakao")
    public String home(){
        return "kakao";
    }

    @GetMapping("/home")
    public String h1(){
        return "home";
    }

    @RequestMapping("/LoginForm")
    public String loginForm(){
        return "LoginForm";
    }

    @GetMapping("/JoinForm")
    public String joinForm(){
        return "JoinForm";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
