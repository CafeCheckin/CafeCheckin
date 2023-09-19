package com.example.CafeTour.board.boarddto;

import com.example.CafeTour.board.Board;
import com.example.CafeTour.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
