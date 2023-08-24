package com.example.CafeTour.controller;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.service.BoardService;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

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

    @PostMapping("/write-board") //작성한 게시글 저장
    public ModelAndView boardList(Board board, Principal principal,ModelAndView mav) {
        User userDto = userService.findByEmail(principal.getName());
        boardService.write(board, userDto);
        mav.setViewName("redirect:/board");
        return mav;
    }

    @GetMapping("/board")  //게시글 목록
    public ModelAndView list(ModelAndView mav, @PageableDefault(size = 5,sort = "id",direction = Sort.Direction.DESC)Pageable pageable) {
        mav.addObject("li", boardService.boardingList(pageable));
        mav.addObject("previous", pageable.previousOrFirst().getPageNumber());
        mav.addObject("next", pageable.next().getPageNumber());
        mav.setViewName("board_list");
        return mav;
    }

    @GetMapping("/view") //게시글 상세조회
    public ModelAndView findById(Long boardId,ModelAndView mav,Principal principal){ //게시글의 번호(Id)값을 인자로 받음
        mav.addObject("boarddetail",boardService.details(boardId));
        Board board=boardService.details(boardId);
        if(principal.getName().equals(board.getUser().getEmail())){ //글을 작성한 user와 로그인한 사림이 일치한경우
            mav.setViewName("board_details");
            return mav;
        }
        else{
            mav.setViewName("notuser_writeboard_details");
            return mav;
        }
    }

    @GetMapping("/delete-board")
    public ModelAndView deleteBoarding(Long boardId,ModelAndView mav){
        boardService.deleteById(boardId);
        mav.setViewName("redirect:/board");
        return mav;
    }

    @PostMapping("/update-board")
    public ModelAndView boardUpdate(Long boardId,Board board,ModelAndView mav) {
        boardService.updateBoard(board,boardId);
        mav.setViewName("redirect:/board");
        return mav;
    }

    @GetMapping("/update")
    public ModelAndView boardUpdateForm(Long boardId,ModelAndView mav) {
        mav.addObject("boarddetail",boardService.details(boardId));
        mav.setViewName("board_update");
        return mav;
    }
}