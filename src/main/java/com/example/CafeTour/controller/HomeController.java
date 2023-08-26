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
    public ModelAndView h1(ModelAndView mav) {
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping("/lll")
    public ModelAndView pra1(ModelAndView mav) {
        mav.setViewName("lll");
        return mav;
    }

    @RequestMapping("/cafe")
    public ModelAndView LoginHome(Principal principal, ModelAndView mav) {
        mav.addObject("userinfo1", principal.getName());
        mav.setViewName("cafe");
        return mav;
    }

    @GetMapping("/auth/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        ModelAndView mav) {
        mav.addObject("error", error);
        mav.addObject("exception", exception);
        mav.setViewName("lll");
        return mav;
    }
}
