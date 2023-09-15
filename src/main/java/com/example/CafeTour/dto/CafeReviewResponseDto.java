package com.example.CafeTour.dto;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.CafeReview;
import com.example.CafeTour.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
public class CafeReviewResponseDto {
    private Long id;
    private String reviewText;
    private User user;
    private CafeInformation cafeInformation;
    private int grade;
    private int reviewHit;

    @Builder
    public CafeReviewResponseDto(CafeReview cafeReview) {
        this.id = cafeReview.getId();
        this.reviewText=cafeReview.getReviewText();
        this.user= cafeReview.getUser();
        this.cafeInformation=cafeReview.getCafeInformation();
        this.grade=cafeReview.getGrade();
        this.reviewHit= cafeReview.getReviewHit();
    }
}
