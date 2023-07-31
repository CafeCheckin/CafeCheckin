package com.example.CafeTour.controller;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.service.CafeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.PastOrPresent;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("/cafe")
    public String cafeInformation(Model model){
        List<CafeInformation> cafeInformations=cafeService.findCafe();
        model.addAttribute("cafe",cafeInformations);
        return "cafe";
    }

    @GetMapping("/SeoulCafe")
    public String gangNam(HttpServletRequest httpServletRequest){
        String name=httpServletRequest.getParameter("seoul");
        System.out.println("name "+name);
        return "/SeoulCafe/"+name;
    }
}
