package com.example.CafeTour.cafeimage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeImageRepository extends JpaRepository<CafeImage,Long> {
    List<CafeImage> findByCafeInformationId(Long cafeId);
}
