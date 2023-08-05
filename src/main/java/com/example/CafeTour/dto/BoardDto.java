package com.example.CafeTour.dto;

import com.example.CafeTour.domain.Board;
import lombok.Builder;
import lombok.Getter;
import java.sql.Timestamp;

@Getter
@Builder
public class BoardDto {
    private Long id;
    private String userNickName;
    private int clicks;
    private String title;
    private String boardOpinion;
    private Timestamp createDAte;
    private Timestamp modifyDate;

    public static BoardDto boardDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .userNickName(board.getUser().getNickName())
                .clicks(board.getClicks())
                .title(board.getTitle())
                .boardOpinion(board.getBoardOpinion())
                .createDAte(board.getCreateDate())
                .modifyDate(board.getModifyDate())
                .build();
    }


}
