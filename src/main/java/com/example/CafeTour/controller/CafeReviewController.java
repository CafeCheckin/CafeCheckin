package com.example.CafeTour.controller;

import com.example.CafeTour.Message;
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
        mav.setViewName("/reviews/write_review");
        return mav;
    }

    @PostMapping("/write-review") //작성한 리뷰 저장
    public ModelAndView reviewList(Long cafeId,CafeReview cafeReview, Principal principal,ModelAndView mav) {
        User userDto = userService.findByEmail(principal.getName());
        cafeReviewService.write(cafeReview, userDto,cafeService.details(cafeId));
        mav.addObject("data", new Message("리뷰 작성이 완료되었습니다", "/cafe-info/"+cafeId));
        mav.setViewName("Message");
        return mav;
    }

    @GetMapping("/delete-review")
    public ModelAndView deleteReview(Long reviewId,Long cafeId,ModelAndView mav){ //리뷰의 Id
        cafeReviewService.deleteById(reviewId);
        mav.addObject("data", new Message("선택하신 리뷰가 삭제되었습니다.", "/cafe-info/"+cafeId));
        mav.setViewName("Message");
        return mav;
    }

    @GetMapping("/review-update")
    public ModelAndView reviewUpdateForm(Long reviewId,Long cafeId, ModelAndView mav) {
        mav.addObject("reviewdetail",cafeReviewService.details(reviewId));
        mav.addObject("cafeInfo",cafeId);
        System.out.println(cafeReviewService.details(reviewId));
        System.out.println(cafeId);
        mav.setViewName("/reviews/update_review");
        return mav;
    }

    @PostMapping("/update-review")
    public ModelAndView reviewUpdate(Long reviewId,Long cafeId, CafeReview cafeReview,ModelAndView mav) {
        cafeReviewService.updateReview(cafeReview,reviewId);
        mav.addObject("data", new Message("리뷰가 수정되었습니다", "/cafe-info/"+cafeId));
        mav.setViewName("Message");
        return mav;
    }

    @GetMapping("/review-detail")
    public ModelAndView findById(Long reviewId,Long cafeId,ModelAndView mav,Principal principal){
        CafeReview cafeReview=cafeReviewService.details(reviewId);
        cafeReviewService.updateView(reviewId);
        mav.addObject("reviewdetail",cafeReview);
        mav.addObject("cafeInfo",cafeId);
        if(principal.getName().equals(cafeReview.getUser().getEmail())){ //글을 작성한 user와 로그인한 사림이 일치한경우
            mav.setViewName("/reviews/review_detail");
            return mav;
        }
        else{
            mav.setViewName("/reviews/not_review_detail");
            return mav;
        }
    }
}
