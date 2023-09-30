package com.example.CafeTour.cafereview;

import com.example.CafeTour.cafeinformation.CafeInformation;
import com.example.CafeTour.BaseTimeEntity;
import com.example.CafeTour.cafereview.cafereviewdto.CafeReviewUpdateRequestDto;
import com.example.CafeTour.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cafe_review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeReview extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Lob
    private String reviewText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="cafe_information_id")
    @JsonIgnore
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
