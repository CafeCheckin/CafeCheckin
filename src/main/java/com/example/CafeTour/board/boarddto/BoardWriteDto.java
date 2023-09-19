package com.example.CafeTour.board.boarddto;

import com.example.CafeTour.board.Board;
import com.example.CafeTour.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardWriteDto {
    private Long id;
    private String userNickName;
    private int clicks;
    private String title;
    private String boardOpinion;
    private User user;

    @Builder
    public BoardWriteDto(Board board){
        this.id= board.getId();
        this.userNickName=board.getUser().getNickName();
        this.clicks=board.getClicks();
        this.title=board.getTitle();
        this.boardOpinion=board.getBoardOpinion();
        this.user=board.getUser();
    }
}
