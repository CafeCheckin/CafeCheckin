package com.example.CafeTour.comment.commentdto;

import com.example.CafeTour.board.Board;
import com.example.CafeTour.comment.Comment;
import com.example.CafeTour.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
