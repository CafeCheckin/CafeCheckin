package com.example.CafeTour.dto;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.Comment;
import com.example.CafeTour.domain.User;
import lombok.Builder;
import lombok.Getter;
import java.sql.Timestamp;

@Getter
public class CommentResponseDto {

    private Long id;
    private String commentText;
    private Board board;
    private User user;
    private Timestamp createDate;
    private Timestamp modifyDate;

    @Builder
    public CommentResponseDto(Comment comment) {
        this.id=comment.getId();
        this.commentText=comment.getCommentText();
        this.board=comment.getBoard();
        this.user= comment.getUser();
    }
}
