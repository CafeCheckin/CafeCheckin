package com.example.CafeTour.dto;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserWish;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
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
