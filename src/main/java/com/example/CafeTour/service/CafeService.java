package com.example.CafeTour.service;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.repository.CafeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;

    public List<CafeInformation> findCafe(){
        return cafeRepository.findAll();
    }
}