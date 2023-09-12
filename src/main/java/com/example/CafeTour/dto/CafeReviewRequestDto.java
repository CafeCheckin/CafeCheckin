package com.example.CafeTour.dto;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.CafeReview;
import com.example.CafeTour.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CafeReviewRequestDto {
    private String reviewText;
    private User user;
    private int grade;

    public CafeReview toEntity(User user,CafeInformation cafeInformation) {
        return CafeReview.builder()
                .user(user)
                .cafeInformation(cafeInformation)
                .reviewText(reviewText)
                .grade(grade)
                .build();
    }
}
