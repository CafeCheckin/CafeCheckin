package com.example.CafeTour.dto;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
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
