package com.example.CafeTour.domain;

import com.example.CafeTour.dto.CafeReviewUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@NoArgsConstructor
public class CafeReview extends BaseTimeEntity{
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

    @Column(name = "grade", columnDefinition = "integer default 2",nullable = false)
    private int grade;

    @Column(name = "review_hit")
    private int reviewHit;

    @Builder
    public CafeReview(Long id, String reviewText, User user, CafeInformation cafeInformation, int grade) {
        this.id = id;
        this.reviewText = reviewText;
        this.user = user;
        this.cafeInformation = cafeInformation;
        this.grade = grade;
        this.reviewHit = 0;
    }

    public void update(CafeReviewUpdateRequestDto requestDto){
        this.reviewText=requestDto.getReviewText();
        this.grade=requestDto.getGrade();
    }
}
