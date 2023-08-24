package com.example.CafeTour.controller;

import com.example.CafeTour.domain.CafeReview;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.service.CafeReviewService;
import com.example.CafeTour.service.CafeService;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CafeReviewController {
    private final UserService userService;
    private final CafeReviewService cafeReviewService;
    private final CafeService cafeService;

    @GetMapping("/write-review")
    public ModelAndView writeReview(Long cafeId, ModelAndView mav){
        mav.addObject("cafeInfo",cafeId);
        mav.setViewName("write_review");
        return mav;
    }

    @PostMapping("/write-review") //작성한 리뷰 저장
    public ModelAndView reviewList(Long cafeId,CafeReview cafeReview, Principal principal,ModelAndView mav) {
        User userDto = userService.findByEmail(principal.getName());
        cafeReviewService.write(cafeReview, userDto,cafeService.details(cafeId));
        mav.setViewName("redirect:/cafe-info/"+cafeId);
        return mav;
    }

    @GetMapping("/delete-review")
    public ModelAndView deleteReview(Long reviewId,Long cafeId,ModelAndView mav){ //리뷰의 Id
        cafeReviewService.deleteById(reviewId);
        mav.setViewName("redirect:/cafe-info/"+cafeId);
        return mav;
    }

    @GetMapping("/review-update")
    public ModelAndView reviewUpdateForm(Long id,ModelAndView mav,Long cafeId) {
        mav.addObject("reviewdetail",cafeReviewService.details(id));
        mav.addObject("cafeInfo",cafeId);
        mav.setViewName("update_review");
        return mav;
    }

    @PostMapping("/update-review")
    public ModelAndView reviewUpdate(Long reviewId,Long cafeId, CafeReview cafeReview,ModelAndView mav) {
        cafeReviewService.updateReview(cafeReview,reviewId);
        mav.setViewName("redirect:/cafe-info/"+cafeId);
        return mav;
    }

    @GetMapping("/review-detail")
    public ModelAndView findById(Long reviewId,Long cafeId,ModelAndView mav,Principal principal){
        mav.addObject("reviewdetail",cafeReviewService.details(reviewId));
        mav.addObject("cafeinfo",cafeId);
        CafeReview cafeReview=cafeReviewService.details(reviewId);
        if(principal.getName().equals(cafeReview.getUser().getEmail())){ //글을 작성한 user와 로그인한 사림이 일치한경우
            mav.setViewName("review_detail");
            return mav;
        }
        else{
            mav.setViewName("not_review_detail");
            return mav;
        }
    }
}
