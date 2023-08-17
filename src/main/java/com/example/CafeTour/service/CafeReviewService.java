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

    @Transactional
    public void deleteById(Long id) {
        cafeReviewRepository.deleteById(id);
    }
}
