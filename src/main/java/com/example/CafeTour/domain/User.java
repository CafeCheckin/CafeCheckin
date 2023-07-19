package com.example.CafeTour.domain;

import com.example.CafeTour.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
public class User{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "Pw")
    private String Pw;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "user_create_dt")
    @CreationTimestamp
    private Timestamp userCreateDt;

    public User() {

    }

    public static User toUser(UserDto userDto){
       User user=new User();
       user.setPw(userDto.getPw());
       user.setNickName(userDto.getNickName());
       user.setUserCreateDt(userDto.getUserCreateDt());
       return user;
    }

}