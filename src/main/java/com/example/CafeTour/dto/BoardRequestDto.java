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
    private String title;
    private String boardOpinion;
   public Board toEntity(User user){
       return Board.builder()
               .user(user)
               .title(title)
               .boardOpinion(boardOpinion)
               .build();
   }
}
