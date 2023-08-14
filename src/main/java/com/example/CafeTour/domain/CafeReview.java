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
public class CafeReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Lob
    private String reviewText;

    @JoinColumn(name ="user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name ="cafe_information_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private CafeInformation cafeInformation;

    @Column(name = "grade")
    private int grade;

    @Column(name = "title")
    private String title;

    @Column(name = "review_hit")
    private int reviewHit;

    @Column(name = "create_dt")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "modify_dt")
    @UpdateTimestamp
    private Timestamp modifyDate;
}
