package com.example.CafeTour.dto;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String boardOpinion;
    private Timestamp modifyDate;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .boardOpinion(boardOpinion)
                .modifyDate(modifyDate)
                .build();
    }
}
