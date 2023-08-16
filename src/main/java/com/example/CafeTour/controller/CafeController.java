package com.example.CafeTour.controller;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("/SeoulCafe")
    public String gangNam(HttpServletRequest httpServletRequest, Model model){
        String name=httpServletRequest.getParameter("seoul");
        System.out.println(name);
        model.addAttribute("location",cafeService.findCafe(name));
        model.addAttribute("mess","성공");
        return "home";
       // return "/SeoulCafe/"+name;
    }

    @GetMapping("/cafeinfo/{id}") //카페 상세조회
    public String findById(@PathVariable Long id, Model model){ //카페의 번호(Id)값을 인자로 받음
        model.addAttribute("locations",cafeService.details(id));
        return "CafeDetail";
    }

}
