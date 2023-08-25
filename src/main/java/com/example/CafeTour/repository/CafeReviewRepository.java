package com.example.CafeTour.repository;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.CafeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeReviewRepository extends JpaRepository<CafeReview,Long> {
    @Override
    Optional<CafeReview> findById(Long aLong);


    List<CafeReview> findByCafeInformationIdOrderByCreateDateDesc(Long id);
}
