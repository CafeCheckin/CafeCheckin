package com.example.CafeTour.controller;

import com.example.CafeTour.dto.CafeReviewRequestDto;
import com.example.CafeTour.dto.CafeReviewResponseDto;
import com.example.CafeTour.dto.CafeReviewUpdateRequestDto;
import com.example.CafeTour.service.CafeReviewService;
import com.example.CafeTour.service.CafeService;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class CafeReviewController {
    private final CafeReviewService cafeReviewService;

    @PostMapping("/write-review/{cafeId}") //작성한 리뷰 저장
    public Long reviewList(@PathVariable Long cafeId, @RequestBody CafeReviewRequestDto requestDto, Principal principal) {
        return cafeReviewService.write(cafeId,requestDto,principal.getName());
    }

    @DeleteMapping("/delete-review/{reviewId}") //리뷰 삭제
    public String deleteReview(@PathVariable Long reviewId, Long cafeId) { //리뷰의 Id
        cafeReviewService.deleteById(reviewId);
        return "삭제 완료";
    }

    @PatchMapping("/update-review/{reviewId}") //리뷰 업데이트
    public Long reviewUpdate(@PathVariable Long reviewId, Long cafeId, @RequestBody CafeReviewUpdateRequestDto requestDto) {
        cafeReviewService.updateReview(reviewId,requestDto);
        return reviewId;
    }

    @GetMapping("/review-detail/{reviewId}") //리뷰 상세보기
    public CafeReviewResponseDto findById(@PathVariable Long reviewId) {
       return cafeReviewService.details(reviewId);
    }
}
