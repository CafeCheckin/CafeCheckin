package com.example.CafeTour.service;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.CafeReview;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.CafeResponseDto;
import com.example.CafeTour.dto.CafeReviewRequestDto;
import com.example.CafeTour.dto.CafeReviewResponseDto;
import com.example.CafeTour.dto.CafeReviewUpdateRequestDto;
import com.example.CafeTour.repository.CafeRepository;
import com.example.CafeTour.repository.CafeReviewRepository;
import com.example.CafeTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeReviewService {
    private final CafeReviewRepository cafeReviewRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;

    @Transactional
    public Long write(Long cafeId, CafeReviewRequestDto requestDto,String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 유저"));
        CafeInformation cafeInformation = cafeRepository.findById(cafeId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 카페"));
        return cafeReviewRepository.save(requestDto.toEntity(user,cafeInformation)).getId();

    } //리뷰 글 작성


    @Transactional(readOnly = true)
    public List<CafeReview> reviewList(Long id) {
        return cafeReviewRepository.findByCafeInformationIdOrderByCreateDateDesc(id);
    }

    public void deleteById(Long id) {
        cafeReviewRepository.deleteById(id);
    } //리뷰 삭제

    @Transactional
    public void updateReview(Long reviewId, CafeReviewUpdateRequestDto requestDto) {
        CafeReview persistance=cafeReviewRepository.findById(reviewId).
                orElseThrow(()->new IllegalArgumentException("존재하지 않는 리뷰"));
        persistance.update(requestDto);
    } //리뷰 수정

    @Transactional
    public CafeReviewResponseDto details(Long reviewId){ //리뷰 상세 조회
        CafeReview cafeReview=cafeReviewRepository.findById(reviewId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 리뷰"));
        return new CafeReviewResponseDto(cafeReview);
    }
}
