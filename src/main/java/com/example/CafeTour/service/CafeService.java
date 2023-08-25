package com.example.CafeTour.service;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;

    public List<CafeInformation> findCafe(String name){
        return cafeRepository.findByAddressContaining(name);
    }

    @Transactional(readOnly = true)
    public CafeInformation details(Long id){
        return cafeRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("존재하지 않는 카페");
                });
    } //카페 세부사항 조회
}
