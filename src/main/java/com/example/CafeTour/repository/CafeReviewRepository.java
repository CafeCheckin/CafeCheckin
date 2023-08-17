package com.example.CafeTour.repository;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.CafeReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeReviewRepository extends JpaRepository<CafeReview,Long> {
    List<CafeReview> findByCafeInformationIdOrderByCreateDateDesc(Long id);
}
