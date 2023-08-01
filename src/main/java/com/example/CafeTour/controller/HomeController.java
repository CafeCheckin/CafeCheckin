package com.example.CafeTour.controller;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.repository.UserRepository;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/kakao")
    public String home(){
        return "kakao";
    }

    @RequestMapping("/home")
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

    @GetMapping("homepage")
    public String homepage(){
        return "homepage";
    }

    @GetMapping("/pra")
    public String pra(){
        return "pra";
    }

    @RequestMapping("/lll")
    public String pra1(){
        return "lll";
    }

    @RequestMapping("/cafe")
    public String LoginHome(){
        return "cafe";
    }

    @GetMapping("/userinfo")
    public String userInfo(Principal principal,Model model){
        System.out.println(principal.getName());
        model.addAttribute("userinfo1",principal.getName());
        return "cafe";
    }

    @GetMapping("/userinfo2")
    public String findByEmail(Model model,Principal principal){
        User userDto=userService.findByEmail(principal.getName());
        model.addAttribute("userinfo2",userDto);
        return "UserInfo";
    }

}
