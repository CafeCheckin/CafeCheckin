package com.example.CafeTour.cafeimage;

import com.example.CafeTour.cafeimage.CafeImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeImageRepository extends JpaRepository<CafeImage,Long> {
    List<CafeImage> findByCafeInformationId(Long cafeId);
}
