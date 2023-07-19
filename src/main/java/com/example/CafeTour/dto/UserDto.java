package com.example.CafeTour.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UserDto {

    private String Pw;

    private String email;

    private String nickName;

    @CreationTimestamp
    private Timestamp userCreateDt;

    public UserDto(String pw, String email, String nickName) {
        Pw = pw;
        this.email = email;
        this.nickName = nickName;
    }

}
