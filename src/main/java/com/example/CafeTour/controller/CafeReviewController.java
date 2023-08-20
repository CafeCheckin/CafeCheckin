package com.example.CafeTour.controller;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.CafeInformation;
import com.example.CafeTour.domain.CafeReview;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.service.CafeReviewService;
import com.example.CafeTour.service.CafeService;
import com.example.CafeTour.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CafeReviewController {
    private final UserService userService;
    private final CafeReviewService cafeReviewService;
    private final CafeService cafeService;

    @GetMapping("/writereview")
    public String writeReview(Long id,Model model){
        model.addAttribute("cafeinfo",id);
        return "WriteReview";
    }

    @PostMapping("/writereview") //작성한 리뷰 저장
    public String reviewList(Long id,CafeReview cafeReview, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        cafeReviewService.write(cafeReview, userDto,cafeService.details(id));
        return "redirect:/cafeinfo/"+id;
    }

    @GetMapping("/deletereview")
    public String deleteReview(Long id,Long cid){ //리뷰의 Id
        cafeReviewService.deleteById(id);
        return "redirect:/cafeinfo/"+cid;
    }

    @GetMapping("/reviewupdate")
    public String reviewUpdateForm(Long id,Model model,Long cid) {
        model.addAttribute("reviewdetail",cafeReviewService.details(id));
        model.addAttribute("cafeinfo",cid);
        return "UpdateReview";
    }

    @PostMapping("/updatereview")
    public String reviewUpdate(Long id,CafeReview cafeReview,Long cid) {
        cafeReviewService.updateReview(cafeReview,id);
        return "redirect:/cafeinfo/"+cid;
    }
}
