package com.example.CafeTour.comment;

import com.example.CafeTour.board.Board;
import com.example.CafeTour.BaseTimeEntity;
import com.example.CafeTour.comment.commentdto.CommentUpdateRequestDto;
import com.example.CafeTour.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity { //댓글 구현
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Lob
    private String commentText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    @Builder
    public Comment(Long id, String commentText, Board board, User user) {
        this.id = id;
        this.commentText = commentText;
        this.board = board;
        this.user = user;
    }

    public void update(CommentUpdateRequestDto requestDto){
        this.commentText=requestDto.getCommentText();
    }
}
