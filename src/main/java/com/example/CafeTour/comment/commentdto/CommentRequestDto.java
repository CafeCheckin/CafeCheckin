package com.example.CafeTour.comment.commentdto;

import com.example.CafeTour.board.Board;
import com.example.CafeTour.comment.Comment;
import com.example.CafeTour.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String commentText;

    public Comment toEntity(User user,Board board){
        return Comment.builder()
                .commentText(commentText)
                .user(user)
                .board(board)
                .build();
    }
}
