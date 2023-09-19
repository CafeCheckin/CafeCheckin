package com.example.CafeTour.cafeimage;

import com.example.CafeTour.config.mail.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class CafeImageController {
    @GetMapping("/insert")
    public ModelAndView insertImage(ModelAndView mav, Long cafeId) {
        mav.addObject("cafeInfo",cafeId);
        mav.setViewName("/insert_image");
        return mav;
    }

    @PostMapping("/image-upload")
    public ModelAndView imageUpload(@RequestParam("cafeImageUrl")String cafeImageUrl,Long cafeId,ModelAndView mav) {
       // cafeImageService.upload(cafeImageUrl,cafeService.details(cafeId));
        mav.addObject("data", new Message("이미지 등록 성공!", "/cafe-info/"+cafeId));
        mav.setViewName("Message");
        return mav;
    }
}
