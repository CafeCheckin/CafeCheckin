package com.example.CafeTour.board.boarddto;

import com.example.CafeTour.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String boardOpinion;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .boardOpinion(boardOpinion)
                .build();
    }
}
