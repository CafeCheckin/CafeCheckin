package com.example.CafeTour.dto;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.Comment;
import com.example.CafeTour.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private Long id;
    private String commentText;
    private Board board;
    private User user;

    public Comment toEntity(){
        return Comment.builder()
                .commentText(commentText)
                .build();
    }
}
