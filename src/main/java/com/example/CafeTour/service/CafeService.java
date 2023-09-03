package com.example.CafeTour.service;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.repository.CafeRepository;
import com.example.CafeTour.repository.CafeReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;
    private final CafeReviewRepository cafeReviewRepository;

    @Transactional
    public List<CafeInformation> findCafe(String name) {
        return cafeRepository.findByAddressContaining(name);
    }

    @Transactional(readOnly = true)
    public CafeInformation details(Long id) {
        return cafeRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("존재하지 않는 카페");
                });
    } //카페 세부사항 조회

    @Transactional
    public void updateGrade(int grade, Long cafeId, int size) {
        Optional<CafeInformation> cafeInformation = cafeRepository.findById(cafeId);
        System.out.println("카페이름: " + cafeInformation.get().getCafeName());
        double gradeSum = cafeReviewRepository.showGrade(cafeId); //현재 카페 총 리뷰 점수 12
        gradeSum /= size;
        cafeInformation.get().setCafeGrade(gradeSum);
        cafeRepository.save(cafeInformation.get());
    }
}
