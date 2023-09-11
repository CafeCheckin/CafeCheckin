package com.example.CafeTour.controller;

import com.example.CafeTour.dto.CafeResponseDto;
import com.example.CafeTour.service.CafeImageService;
import com.example.CafeTour.service.CafeReviewService;
import com.example.CafeTour.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;
    private final CafeReviewService cafeReviewService;
    private final CafeImageService cafeImageService;

    @GetMapping("/cafe-info/{cafeId}") //카페 상세조회
    public CafeResponseDto findById(@PathVariable Long cafeId) { //카페의 번호(Id)값을 인자로 받음
        return cafeService.details(cafeId);
    }
}
