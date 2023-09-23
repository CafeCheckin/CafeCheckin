package com.example.CafeTour.cafereview;

import com.example.CafeTour.cafeinformation.CafeInformation;
import com.example.CafeTour.user.User;
import com.example.CafeTour.cafereview.cafereviewdto.CafeReviewRequestDto;
import com.example.CafeTour.cafereview.cafereviewdto.CafeReviewResponseDto;
import com.example.CafeTour.cafereview.cafereviewdto.CafeReviewUpdateRequestDto;
import com.example.CafeTour.cafeinformation.CafeRepository;
import com.example.CafeTour.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CafeReviewService {
    private final CafeReviewRepository cafeReviewRepository;
    private final CafeRepository cafeRepository;
    private final UserService userService;

    public Long write(Long cafeId, CafeReviewRequestDto requestDto,String email) {
        User user=userService.isError(email);
        CafeInformation cafeInformation = cafeRepository.findById(cafeId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 카페"));
        return cafeReviewRepository.save(requestDto.toEntity(user,cafeInformation)).getId();
    } //리뷰 글 작성


    @Transactional(readOnly = true)
    public List<CafeReview> reviewList(Long id) {
        return cafeReviewRepository.findByCafeInformationIdOrderByCreateDateDesc(id);
    }

    public void updateReview(Long reviewId, CafeReviewUpdateRequestDto requestDto) {
        CafeReview persistence=isError(reviewId);
        persistence.update(requestDto);
    } //리뷰 수정

    public CafeReviewResponseDto details(Long reviewId){ //리뷰 상세 조회
        CafeReview cafeReview=isError(reviewId);
        return new CafeReviewResponseDto(cafeReview);
    }

    public CafeReview isError(Long reviewId){
        return cafeReviewRepository.findById(reviewId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 리뷰"));
    }
}
