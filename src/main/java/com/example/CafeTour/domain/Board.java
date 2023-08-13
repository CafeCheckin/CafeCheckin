package com.example.CafeTour.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "boarding")
@Data
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @JoinColumn(name ="user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //mappedBy는 연관관계의주인X, FK가 아니니 컬럼생성X
    private List<Reply> reply;

    @Lob
    private String boardOpinion;

    @Column(name = "clicks")
    private int clicks;

    @Column(name = "title")
    private String title;

    @Column(name = "create_date")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "modify_date")
    @UpdateTimestamp
    private Timestamp modifyDate;
}
