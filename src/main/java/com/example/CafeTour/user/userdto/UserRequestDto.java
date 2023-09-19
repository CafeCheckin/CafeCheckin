package com.example.CafeTour.user.userdto;

import com.example.CafeTour.user.User;
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
