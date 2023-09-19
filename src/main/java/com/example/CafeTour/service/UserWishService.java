package com.example.CafeTour.service;

import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserWish;
import com.example.CafeTour.dto.UserWishRequestDto;
import com.example.CafeTour.dto.UserWishResponseDto;
import com.example.CafeTour.repository.CafeRepository;
import com.example.CafeTour.repository.UserRepository;
import com.example.CafeTour.repository.UserWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWishService {
    private final UserWishRepository userWishRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
    private final UserService userService;
    @Transactional
    public Long register(Long cafeId, String email, UserWishRequestDto requestDto) {
        User user=userService.isError(email);
        CafeInformation cafeInformation=cafeRepository.findById(cafeId).orElseThrow(()
                ->new IllegalArgumentException("존재하지 않는 카페"));
        return userWishRepository.save(requestDto.toEntity(user,cafeInformation)).getId();
    }

    @Transactional
    public void findWishList(String email,UserWishResponseDto responseDto){
        User user=userService.isError(email);
    }

    @Transactional
    public boolean checkWishList(Long cafeId,Long userId){
      return userWishRepository.existsByCafeInformation_IdAndUser_Id(cafeId,userId);
    }

    public void delete(Long wishListId) {
        userWishRepository.deleteById(wishListId);
    }
}
