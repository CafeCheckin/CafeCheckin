package com.example.CafeTour.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "boarding")
@Data
@Component
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @JoinColumn(name ="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "board_opinion")
    private String boardOpinion;

    @Column(name = "clicks")
    private int clicks;

    @Column(name = "title")
    private String title;

    @Column(name = "create_date")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "modify_date")
    @CreationTimestamp
    private Timestamp modifyDate;
}
