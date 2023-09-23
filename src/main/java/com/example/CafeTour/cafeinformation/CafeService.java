package com.example.CafeTour.cafeinformation;

import com.example.CafeTour.cafeinformation.cafeinformationdto.CafeResponseDto;
import com.example.CafeTour.cafereview.CafeReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {
    private final CafeRepository cafeRepository;
    private final CafeReviewRepository cafeReviewRepository;

    public CafeResponseDto details(Long id) {
       CafeInformation cafeInformation=cafeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않은 카페"));
       return new CafeResponseDto(cafeInformation);
    } //카페 세부사항 조회

    @Transactional
    public void updateGrade(int grade, Long cafeId, int size) { //카페 평점 등록
        Optional<CafeInformation> cafeInformation = cafeRepository.findById(cafeId);
        double gradeSum = cafeReviewRepository.showGrade(cafeId); //현재 카페 총 리뷰 점수 12
        gradeSum /= size;
        cafeInformation.get().updateCafeGrade(gradeSum);
        cafeRepository.save(cafeInformation.get());
    }
}
