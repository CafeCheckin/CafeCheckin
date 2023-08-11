package com.example.CafeTour.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
