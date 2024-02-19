package com.example.CafeTour.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UserDto {
    private Long Id;

    private String Pw;

    private String email;

    private String nickName;

    @CreationTimestamp
    private Timestamp userCreateDt;

    public UserDto(Long Id,String pw, String email, String nickName) {
        this.Id=Id;
        this.Pw = pw;
        this.email = email;
        this.nickName = nickName;
    }


}
