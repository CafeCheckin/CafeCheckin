package com.example.CafeTour.userwish.userwishdto;

import com.example.CafeTour.cafeinformation.CafeInformation;
import com.example.CafeTour.user.User;
import com.example.CafeTour.userwish.UserWish;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UserWishResponseDto {
    private Long id;
    private User user;
    private List<CafeInformation> cafeInformation;

    @Builder
    public UserWishResponseDto(UserWish userWish) {
        this.id = user.getId();
        this.user=userWish.getUser();
        this.cafeInformation= (List<CafeInformation>) userWish.getCafeInformation();
    }
}
