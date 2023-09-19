package com.example.CafeTour.comment.commentdto;

import com.example.CafeTour.board.Board;
import com.example.CafeTour.comment.Comment;
import com.example.CafeTour.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String commentText;
    private Board board;
    private User user;

    @Builder
    public CommentResponseDto(Comment comment) {
        this.id=comment.getId();
        this.commentText=comment.getCommentText();
        this.board=comment.getBoard();
        this.user= comment.getUser();
    }
}
