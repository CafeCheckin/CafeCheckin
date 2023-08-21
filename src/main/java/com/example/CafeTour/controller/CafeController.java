package com.example.CafeTour.controller;

import com.example.CafeTour.service.CafeReviewService;
import com.example.CafeTour.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;
    private final CafeReviewService cafeReviewService;

    /*@GetMapping("/SeoulCafe")
    public ModelAndView gangNam(HttpServletRequest httpServletRequest, ModelAndView){
        String name=httpServletRequest.getParameter("seoul");
        System.out.println(name);
        model.addAttribute("location",cafeService.findCafe(name));
        model.addAttribute("mess","성공");
        return "/SeoulCafe/"+name;
    }*/

    @GetMapping("/SeoulCafe")
    public ModelAndView gangNam(HttpServletRequest httpServletRequest, ModelAndView mav){
        String name=httpServletRequest.getParameter("seoul");
        mav.addObject("location",cafeService.findCafe(name));
       mav.setViewName("/SeoulCafe/"+name);
        return mav;
    }

    @GetMapping("/cafeinfo/{id}") //카페 상세조회
    public ModelAndView findById(@PathVariable Long id, ModelAndView mav,Principal principal){ //카페의 번호(Id)값을 인자로 받음
        mav.addObject("locations",cafeService.details(id));
        mav.addObject("review",cafeReviewService.reviewList(id));
        mav.setViewName("CafeDetail");
        return mav;
    }
}
