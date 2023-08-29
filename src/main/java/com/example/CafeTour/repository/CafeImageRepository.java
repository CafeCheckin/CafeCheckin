package com.example.CafeTour.repository;

import com.example.CafeTour.domain.CafeImage;
import com.example.CafeTour.domain.CafeReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeImageRepository extends JpaRepository<CafeImage,Long> {
    List<CafeImage> findByCafeInformationId(Long cafeId);
}
