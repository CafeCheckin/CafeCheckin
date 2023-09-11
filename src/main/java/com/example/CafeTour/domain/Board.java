package com.example.CafeTour.domain;

import com.example.CafeTour.dto.BoardUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "boarding")
@Getter
@NoArgsConstructor
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @JoinColumn(name ="user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "board") //mappedBy는 연관관계의주인X, FK가 아니니 컬럼생성X
    private List<Comment> reply;

    @Lob
    private String boardOpinion;

    @Column(name = "clicks",columnDefinition = "integer default 0",nullable = false)
    private int clicks;

    @Column(name = "title")
    private String title;

    @Column(name = "create_date")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "modify_date")
    @UpdateTimestamp
    private Timestamp modifyDate;

    @Builder
    public Board(Long id,User user,String boardOpinion,int clicks,String title,Timestamp createDate,Timestamp modifyDate){
        this.id=id;
        this.user=user;
        this.boardOpinion=boardOpinion;
        this.clicks=clicks;
        this.title=title;
        this.createDate=createDate;
        this.modifyDate=modifyDate;
    }

    public void update(BoardUpdateRequestDto requestDto){
        this.title=requestDto.getTitle();
        this.boardOpinion= requestDto.getBoardOpinion();
        this.modifyDate=requestDto.getModifyDate();
    }
}
