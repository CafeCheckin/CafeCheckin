package com.example.CafeTour.controller;

import com.example.CafeTour.Message;
import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserWish;
import com.example.CafeTour.dto.UserWishRequestDto;
import com.example.CafeTour.dto.UserWishResponseDto;
import com.example.CafeTour.service.CafeService;
import com.example.CafeTour.service.UserService;
import com.example.CafeTour.service.UserWishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserWishController {
    private final CafeService cafeService;
    private final UserWishService userWishService;
    private final UserService userService;

    @GetMapping("/wish-cafe") //카페 찜하기
    public Long wishCafe(Long cafeId, Principal principal, @RequestBody UserWishRequestDto requestDto) {
      return userWishService.register(cafeId,principal.getName(),requestDto);
    }

    @GetMapping("/user-wishlist") //찜한카페 목록 출력
    public void userWishList(Principal principal,UserWishResponseDto responseDto) {
        //return userWishService.findWishList(principal.getName(),responseDto);
    }

    @GetMapping("delete-wishlist") //사용자 위시리스트 삭제
    public String deleteWishList(Long wishListId){
        userWishService.delete(wishListId);
        return "삭제 완료";
    }
}
