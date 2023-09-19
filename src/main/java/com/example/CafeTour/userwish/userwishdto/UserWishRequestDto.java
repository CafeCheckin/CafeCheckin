package com.example.CafeTour.userwish.userwishdto;

import com.example.CafeTour.cafeinformation.CafeInformation;
import com.example.CafeTour.user.User;
import com.example.CafeTour.userwish.UserWish;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserWishRequestDto {
    private Long id;
    private User user;
    private CafeInformation cafeInformation;

    public UserWish toEntity(User user,CafeInformation cafeInformation){
        return UserWish.builder()
                .user(user)
                .cafeInformation(cafeInformation)
                .build();
    }
}
