package com.example.CafeTour.userwish;

import com.example.CafeTour.cafeinformation.CafeInformation;
import com.example.CafeTour.user.User;
import com.example.CafeTour.userwish.userwishdto.UserWishRequestDto;
import com.example.CafeTour.userwish.userwishdto.UserWishResponseDto;
import com.example.CafeTour.cafeinformation.CafeRepository;
import com.example.CafeTour.user.UserRepository;
import com.example.CafeTour.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserWishService {
    private final UserWishRepository userWishRepository;
    private final CafeRepository cafeRepository;
    private final UserService userService;

    public Long register(Long cafeId, String email, UserWishRequestDto requestDto) {
        User user=userService.isError(email);
        CafeInformation cafeInformation=cafeRepository.findById(cafeId).orElseThrow(()
                ->new IllegalArgumentException("존재하지 않는 카페"));
        return userWishRepository.save(requestDto.toEntity(user,cafeInformation)).getId();
    }

    public void findWishList(String email,UserWishResponseDto responseDto){
        User user=userService.isError(email);
    }

    public boolean checkWishList(Long cafeId,Long userId){
      return userWishRepository.existsByCafeInformation_IdAndUser_Id(cafeId,userId);
    }
}
