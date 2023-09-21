package com.example.CafeTour.board;

import com.example.CafeTour.BaseTimeEntity;
import com.example.CafeTour.comment.Comment;
import com.example.CafeTour.board.boarddto.BoardUpdateRequestDto;
import com.example.CafeTour.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boarding",indexes = @Index(name = "idx_board",columnList = "Id"))
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @JsonIgnore
    @JoinColumn(name ="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "board") //mappedBy는 연관관계의주인X, FK가 아니니 컬럼생성X
    private List<Comment> reply;

    @Lob
    private String boardOpinion;

    @Column(name = "clicks",columnDefinition = "integer default 0",nullable = false)
    private int clicks;

    @Column(name = "title")
    private String title;

    @Builder
    public Board(Long id,User user,String boardOpinion,int clicks,String title){
        this.id=id;
        this.user=user;
        this.boardOpinion=boardOpinion;
        this.clicks=clicks;
        this.title=title;
    }

    public void update(BoardUpdateRequestDto requestDto){
        this.title=requestDto.getTitle();
        this.boardOpinion= requestDto.getBoardOpinion();
    }
}
