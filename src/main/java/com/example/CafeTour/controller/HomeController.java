package com.example.CafeTour.controller;

import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.UserDto;
import com.example.CafeTour.repository.UserRepository;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

     @Autowired
     private BCryptPasswordEncoder bCryptPasswordEncoder;


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

    @PostMapping("/join")
    public String join(User user){
        String rawPassword=user.getPw();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);
        user.setPw(encPassword);
        userService.save(user);
        return "redirect:/LoginForm";
    }

    @GetMapping("homepage")
    public String homepage(){
        return "homepage";
    }

}
