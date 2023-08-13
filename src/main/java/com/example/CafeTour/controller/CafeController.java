package com.example.CafeTour.controller;

import com.example.CafeTour.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("/SeoulCafe")
    public String gangNam(HttpServletRequest httpServletRequest){
        String name=httpServletRequest.getParameter("seoul");
        System.out.println("name "+name);
        return "/SeoulCafe/"+name;
    }
}
