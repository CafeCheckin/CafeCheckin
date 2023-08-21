package com.example.CafeTour.controller;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.repository.BoardRepository;
import com.example.CafeTour.service.BoardService;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/boarding") //글 작성
    public ModelAndView boardMain(ModelAndView mav) {
        mav.setViewName("boarding");
        return mav;
    }

    @PostMapping("/writeboard") //작성한 게시글 저장
    public ModelAndView boardList(Board board, Principal principal,ModelAndView mav) {
        User userDto = userService.findByEmail(principal.getName());
        boardService.write(board, userDto);
        mav.setViewName("redirect:/board");
        return mav;
    }

    @GetMapping("/board")  //게시글 목록
    public ModelAndView list(ModelAndView mav, Principal details) {
        mav.addObject("li", boardService.boardingList());
        mav.setViewName("BoardList");
        return mav;
    }

    @GetMapping("/view") //게시글 상세조회
    public ModelAndView findById(Long id,ModelAndView mav,Principal principal){ //게시글의 번호(Id)값을 인자로 받음
        mav.addObject("boarddetail",boardService.details(id));
        Board board=boardService.details(id);
        if(principal.getName().equals(board.getUser().getEmail())){ //글을 작성한 user와 로그인한 사림이 일치한경우
            mav.setViewName("BoardDetails");
            return mav;
        }
        else{
            mav.setViewName("NotUserWriteBoardDetails");
            return mav;
        }
    }

    @GetMapping("/deleteBoard")
    public ModelAndView deleteBoarding(Long id,ModelAndView mav){
        boardService.deleteById(id);
        mav.setViewName("redirect:/board");
        return mav;
    }

    @PostMapping("/updateboard")
    public ModelAndView boardUpdate(Long id,Board board,ModelAndView mav) {
        boardService.updateBoard(board,id);
        mav.setViewName("redirect:/board");
        return mav;
    }

    @GetMapping("/update")
    public ModelAndView boardUpdateForm(Long id,ModelAndView mav) {
        mav.addObject("boarddetail",boardService.details(id));
        mav.setViewName("BoardUpdate");
        return mav;
    }
}