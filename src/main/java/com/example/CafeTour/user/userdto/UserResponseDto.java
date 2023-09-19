package com.example.CafeTour.user.userdto;

import com.example.CafeTour.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private String email;
    private String nickName;
    private String password;

    @Builder
    public UserResponseDto(User user){
        this.email=user.getEmail();
        this.nickName=user.getNickName();
        this.password=user.getPw();
    }
}
