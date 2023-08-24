package com.example.CafeTour.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @RequestMapping("/home")
    public ModelAndView h1(ModelAndView mav){
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping("/lll")
    public ModelAndView pra1(ModelAndView mav){
        mav.setViewName("lll");
        return mav;
    }

    @RequestMapping("/cafe")
    public ModelAndView LoginHome(Principal principal,ModelAndView mav){
        mav.addObject("userinfo1",principal.getName());
        mav.setViewName("cafe");
        return mav;
    }
}
