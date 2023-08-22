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

    @GetMapping("/writereview")
    public ModelAndView writeReview(Long id, ModelAndView mav){
        mav.addObject("cafeinfo",id);
        mav.setViewName("WriteReview");
        return mav;
    }

    @PostMapping("/writereview") //작성한 리뷰 저장
    public ModelAndView reviewList(Long id,CafeReview cafeReview, Principal principal,ModelAndView mav) {
        User userDto = userService.findByEmail(principal.getName());
        cafeReviewService.write(cafeReview, userDto,cafeService.details(id));
        mav.setViewName("redirect:/cafeinfo/"+id);
        return mav;
    }

    @GetMapping("/deletereview")
    public ModelAndView deleteReview(Long id,Long cid,ModelAndView mav){ //리뷰의 Id
        cafeReviewService.deleteById(id);
        mav.setViewName("redirect:/cafeinfo/"+cid);
        return mav;
    }

    @GetMapping("/reviewupdate")
    public ModelAndView reviewUpdateForm(Long id,ModelAndView mav,Long cid) {
        mav.addObject("reviewdetail",cafeReviewService.details(id));
        mav.addObject("cafeinfo",cid);
        mav.setViewName("UpdateReview");
        return mav;
    }

    @PostMapping("/updatereview")
    public ModelAndView reviewUpdate(Long id,CafeReview cafeReview,Long cid,ModelAndView mav) {
        cafeReviewService.updateReview(cafeReview,id);
        mav.setViewName("redirect:/cafeinfo/"+cid);
        return mav;
    }

    @GetMapping("/reviewdetail")
    public ModelAndView findById(Long id,ModelAndView mav,Principal principal,Long cid){
        mav.addObject("reviewdetail",cafeReviewService.details(id));
        mav.addObject("cafeinfo",cid);
        CafeReview cafeReview=cafeReviewService.details(id);
        if(principal.getName().equals(cafeReview.getUser().getEmail())){ //글을 작성한 user와 로그인한 사림이 일치한경우
            mav.setViewName("ReviewDetail");
            return mav;
        }
        else{
            mav.setViewName("NotReviewDetail");
            return mav;
        }
    }
}
