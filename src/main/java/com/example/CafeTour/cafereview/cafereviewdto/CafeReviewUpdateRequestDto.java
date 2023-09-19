package com.example.CafeTour.cafereview.cafereviewdto;

import com.example.CafeTour.cafereview.CafeReview;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeReviewUpdateRequestDto {
    private String reviewText;
    private int grade;

    public CafeReview toEntity(String reviewText, int grade) {
        return CafeReview.builder()
                .reviewText(reviewText)
                .grade(grade)
                .build();
    }
}
