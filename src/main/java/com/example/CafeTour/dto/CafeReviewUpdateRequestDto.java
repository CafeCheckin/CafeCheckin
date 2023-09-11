package com.example.CafeTour.dto;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.CafeReview;
import com.example.CafeTour.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
