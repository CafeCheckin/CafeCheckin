package com.example.CafeTour.user.userdto;

import com.example.CafeTour.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UserDto {
    private String nickName;

    public UserDto(User user) {
        this.nickName = user.getNickName();
    }
}
