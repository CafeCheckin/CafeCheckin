package com.example.CafeTour.service;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserWish;
import com.example.CafeTour.repository.UserWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWishService {
    private final UserWishRepository userWishRepository;

    @Transactional
    public void register(User user, CafeInformation cafeInformation, UserWish userWish) {
        userWish.setUser(user);
        userWish.setCafeInformation(cafeInformation);
        userWishRepository.save(userWish);
    }

    @Transactional
    public List<UserWish> findWishList(Long userId){
        return userWishRepository.findByUserId(userId);
    }
}
