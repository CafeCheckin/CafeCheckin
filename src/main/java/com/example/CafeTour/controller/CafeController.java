package com.example.CafeTour.controller;

import com.example.CafeTour.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("/SeoulCafe")
    public String gangNam(HttpServletRequest httpServletRequest, Model model){
        String name=httpServletRequest.getParameter("seoul");
        System.out.println(name);
        model.addAttribute("location",cafeService.findCafe(name));
        return "/SeoulCafe/"+name;
    }


}
