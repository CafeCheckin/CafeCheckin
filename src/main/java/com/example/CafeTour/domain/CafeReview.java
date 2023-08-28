package com.example.CafeTour.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cafe_review")
@Getter
@Setter
public class CafeReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Lob
    private String reviewText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="cafe_information_id")
    private CafeInformation cafeInformation;

    @Column(name = "grade")
    private int grade;

    @Column(name = "review_hit")
    private int reviewHit;

    @Column(name = "create_dt")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "modify_dt")
    @UpdateTimestamp
    private Timestamp modifyDate;
}
