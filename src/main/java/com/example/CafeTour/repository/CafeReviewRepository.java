package com.example.CafeTour.repository;

import com.example.CafeTour.domain.CafeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CafeReviewRepository extends JpaRepository<CafeReview,Long> {
    @Override
    Optional<CafeReview> findById(Long id);

    List<CafeReview> findByCafeInformationIdOrderByCreateDateDesc(Long id);

    @Modifying
    @Query("update CafeReview b set b.reviewHit = b.reviewHit + 1 where b.id = :id")
    void updateView(Long id);
}
