package com.example.CafeTour.domain;

import com.example.CafeTour.dto.CommentUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Comment { //댓글 구현
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Lob
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "create_dt")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "modify_dt")
    @UpdateTimestamp
    private Timestamp modifyDate;

    @Builder
    public Comment(Long id, String commentText, Board board, User user, Timestamp createDate, Timestamp modifyDate) {
        this.id = id;
        this.commentText = commentText;
        this.board = board;
        this.user = user;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public void update(CommentUpdateRequestDto requestDto){
        this.commentText=requestDto.getCommentText();
    }
}
