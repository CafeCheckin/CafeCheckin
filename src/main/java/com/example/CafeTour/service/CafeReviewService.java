package com.example.CafeTour.service;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.CafeReview;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.repository.CafeReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeReviewService {
    private final CafeReviewRepository cafeReviewRepository;

    @Transactional
    public void write(CafeReview cafeReview, User user, CafeInformation details) {
        cafeReview.setUser(user);
        cafeReview.setCafeInformation(details);
        cafeReviewRepository.save(cafeReview);
    } //리뷰 글 작성


    @Transactional(readOnly = true)
    public List<CafeReview> reviewList(Long id) {
        return cafeReviewRepository.findByCafeInformationIdOrderByCreateDateDesc(id);
    }

    public void deleteById(Long id) {
        cafeReviewRepository.deleteById(id);
    } //리뷰 삭제

    @Transactional
    public void updateReview(CafeReview cafeReview,Long id) {
        CafeReview persistance=cafeReviewRepository.findById(id).orElseThrow(()
                ->{return new IllegalArgumentException("글 찾기 실패");
        });
        persistance.setReviewText(cafeReview.getReviewText());
        persistance.setModifyDate(cafeReview.getModifyDate());
    } //리뷰 수정

    @Transactional
    public CafeReview details(Long id){
        return cafeReviewRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("리뷰 상세보기 실패");
                });
    }

    @Transactional
    public void updateView(Long reviewId) {
        cafeReviewRepository.updateView(reviewId);
    }
}
