package com.example.CafeTour.controller;

import com.example.CafeTour.dto.CafeResponseDto;
import com.example.CafeTour.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("cafe-info/{cafeId}") //카페 상세조회
    public CafeResponseDto findById(@PathVariable Long cafeId) { //카페의 번호(Id)값을 인자로 받음
        return cafeService.details(cafeId);
    }
}
