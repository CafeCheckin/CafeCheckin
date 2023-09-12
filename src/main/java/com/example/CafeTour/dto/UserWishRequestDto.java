package com.example.CafeTour.dto;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserWish;
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
