package com.example.CafeTour.dto;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.Comment;
import com.example.CafeTour.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private Long id;
    private String commentText;
    private Board board;
    private User user;
    private Timestamp createDate;
    private Timestamp modifyDate;

    public Comment toEntity(User user,Board board){
        return Comment.builder()
                .commentText(commentText)
                .user(user)
                .board(board)
                .build();
    }
}
