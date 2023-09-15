package com.example.CafeTour.dto;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.Comment;
import com.example.CafeTour.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
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
