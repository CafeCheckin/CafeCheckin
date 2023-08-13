package com.example.CafeTour.controller;

import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @RequestMapping("/home")
    public String h1(){
        return "home";
    }

    @RequestMapping("/lll")
    public String pra1(){
        return "lll";
    }

    @RequestMapping("/cafe")
    public String LoginHome(Principal principal,Model model){
        System.out.println(principal.getName());
        model.addAttribute("userinfo1",principal.getName());
        return "cafe";
    }
}
