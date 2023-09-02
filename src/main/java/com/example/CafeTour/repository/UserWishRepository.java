package com.example.CafeTour.repository;

import com.example.CafeTour.domain.UserWish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserWishRepository extends JpaRepository<UserWish,Long> {
    List<UserWish> findByUserId(Long userId);

    boolean existsByCafeInformation_IdAndUser_Id(Long cafeId,Long userId);
}
