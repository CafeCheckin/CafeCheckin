package com.example.CafeTour.dto;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {
    private Long id;
    private String userNickName;
    private int clicks;
    private String title;
    private String boardOpinion;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private User user;

   public Board toEntity(User user){
       return Board.builder()
               .id(id)
               .user(user)
               .clicks(clicks)
               .title(title)
               .boardOpinion(boardOpinion)
               .createDate(createDate)
               .modifyDate(modifyDate)
               .build();
   }
}
