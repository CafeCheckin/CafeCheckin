package com.example.CafeTour.board.boarddto;

import com.example.CafeTour.board.Board;
import com.example.CafeTour.comment.Comment;
import com.example.CafeTour.user.User;
import com.example.CafeTour.user.userdto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String userNickName;
    private int clicks;
    private String title;
    private String boardOpinion;
    private User user;
    private List<CommentReponseDto> comments;

    @Builder
    public BoardResponseDto(Board board, List<CommentReponseDto> comments){
        this.id= board.getId();
        this.userNickName=board.getUser().getNickName();
        this.clicks=board.getClicks();
        this.title=board.getTitle();
        this.boardOpinion=board.getBoardOpinion();
        this.user=board.getUser();
        this.comments=comments;
    }

    public BoardResponseDto(Board board) { //게시글 목록 조회 new.ver
        this.id= board.getId();
        this.userNickName=board.getUser().getNickName();
        this.clicks=board.getClicks();
        this.title=board.getTitle();
        this.boardOpinion=board.getBoardOpinion();
    }

    @Getter
    @NoArgsConstructor
    public static class CommentReponseDto{
        private Long id;
        private String commentText;
        private User user;
        private Timestamp createDate;
        private Timestamp modifyDate;

        public CommentReponseDto(Comment comment) {
            this.id = comment.getId();
            this.commentText=comment.getCommentText();
            this.user= comment.getUser();
        }
    }
}
