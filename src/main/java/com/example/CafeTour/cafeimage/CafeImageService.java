package com.example.CafeTour.cafeimage;

import com.example.CafeTour.cafeimage.CafeImage;
import com.example.CafeTour.cafeinformation.CafeInformation;
import com.example.CafeTour.cafeimage.CafeImageRepository;
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
