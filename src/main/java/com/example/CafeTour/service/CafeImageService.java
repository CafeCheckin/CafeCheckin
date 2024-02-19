package com.example.CafeTour.service;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.CafeImage;
import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.repository.CafeImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeImageService {
    private final CafeImageRepository cafeImageRepository;

    public List<CafeImage> uploadImage(Long cafeId){
        return cafeImageRepository.findByCafeInformationId(cafeId);
    }

    @Transactional
    public void upload(String cafeImageUrl, CafeInformation cafeInformation){
        CafeImage cafeImage=new CafeImage();
        cafeImage.setCafeImageUrl(cafeImageUrl);
        cafeImage.setCafeInformation(cafeInformation);
        cafeImageRepository.save(cafeImage);
    } //게시판 글 작성
}
