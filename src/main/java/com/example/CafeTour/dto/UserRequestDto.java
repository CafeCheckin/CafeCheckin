package com.example.CafeTour.dto;

import com.example.CafeTour.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String password;
    public User toEntity(User user){
       return User.builder()
                .email(user.getEmail())
                .build();
    }
}
