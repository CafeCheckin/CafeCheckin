package com.example.CafeTour.controller;

import com.example.CafeTour.Message;
import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserWish;
import com.example.CafeTour.service.CafeService;
import com.example.CafeTour.service.UserService;
import com.example.CafeTour.service.UserWishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserWishController {
    private final CafeService cafeService;
    private final UserWishService userWishService;
    private final UserService userService;

    @GetMapping("/wish-cafe") //카페 찜하기
    public ModelAndView wishCafe(Long cafeId, Principal principal, UserWish userWish, ModelAndView mav) {
        User user = userService.findByEmail(principal.getName());
        CafeInformation cafeInformation = cafeService.details(cafeId);

        if(userWishService.checkWishList(cafeId)){
            mav.addObject("data", new Message("이미 찜이 되어있습니다.", "/cafe-info/" + cafeId));
            mav.setViewName("Message");
            return mav;
        }
        else{
            userWishService.register(user, cafeInformation, userWish);
            mav.addObject("data", new Message("찜하기가 완료되었습니다.", "/cafe-info/" + cafeId));
            mav.setViewName("Message");
            return mav;
        }
    }

    @GetMapping("/user-wishlist") //찜한카페 목록 출력
    public ModelAndView userWishList(Principal principal, ModelAndView mav) {
        User user = userService.findByEmail(principal.getName());
        mav.addObject("wishcafe", userWishService.findWishList(user.getId()));
        mav.setViewName("user_wish_cafe");
        return mav;
    }

    @GetMapping("delete-wishlist")
    public ModelAndView deleteWishList(Long wishListId,ModelAndView mav){
        userWishService.delete(wishListId);
        mav.addObject("data", new Message("카페가 삭제되었습니다.", "/user-wishlist"));
        mav.setViewName("Message");
        return mav;
    }
}
