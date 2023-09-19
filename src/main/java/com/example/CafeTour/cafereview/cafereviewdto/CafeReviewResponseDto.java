package com.example.CafeTour.cafereview.cafereviewdto;

import com.example.CafeTour.cafeinformation.CafeInformation;
import com.example.CafeTour.cafereview.CafeReview;
import com.example.CafeTour.user.User;
import lombok.Builder;
import lombok.Getter;

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
