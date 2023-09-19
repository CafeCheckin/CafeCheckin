package com.example.CafeTour.cafereview.cafereviewdto;

import com.example.CafeTour.cafeinformation.CafeInformation;
import com.example.CafeTour.cafereview.CafeReview;
import com.example.CafeTour.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
