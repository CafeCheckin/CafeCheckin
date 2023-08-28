package com.example.CafeTour.repository;

import com.example.CafeTour.domain.UserWish;
import com.example.CafeTour.service.UserWishService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWishRepository extends JpaRepository<UserWish,Long> {

    List<UserWish> findByUserId(Long userId);
}
