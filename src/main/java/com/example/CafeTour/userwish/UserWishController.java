package com.example.CafeTour.userwish;

import com.example.CafeTour.userwish.userwishdto.UserWishRequestDto;
import com.example.CafeTour.userwish.userwishdto.UserWishResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserWishController {
    private final UserWishService userWishService;
    private final UserWishRepository userWishRepository;

    @GetMapping("/wish-cafe") //카페 찜하기
    public Long wishCafe(Long cafeId, Principal principal, @RequestBody UserWishRequestDto requestDto) {
        return userWishService.register(cafeId, principal.getName(), requestDto);
    }

    @GetMapping("/user-wishlist") //찜한카페 목록 출력
    public void userWishList(Principal principal, UserWishResponseDto responseDto) {
        //return userWishService.findWishList(principal.getName(),responseDto);
    }

    @GetMapping("delete-wishlist") //사용자 위시리스트 삭제
    public String deleteWishList(Long wishListId) {
        userWishRepository.deleteById(wishListId);
        return "삭제 완료";
    }
}
