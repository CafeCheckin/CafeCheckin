package com.example.CafeTour.controller;

import com.example.CafeTour.Message;
import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.BoardWriteDto;
import com.example.CafeTour.service.BoardService;
import com.example.CafeTour.service.CommentService;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping("/boarding") //글 작성
    public BoardWriteDto boardMain(@RequestParam("title")String title) {
        return new BoardWriteDto();
    }

    @PostMapping("/write-board") //작성한 게시글 저장
    public ModelAndView boardList(Board board, Principal principal,ModelAndView mav) {
        User userDto = userService.findByEmail(principal.getName());
        boardService.write(board, userDto);
        mav.addObject("data", new Message("게시글 작성이 완료되었습니다", "/board"));
        mav.setViewName("Message");
        return mav;
    }

    @GetMapping("/board")  //게시글 목록
    public ModelAndView list(ModelAndView mav, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Board> paging=this.boardService.boardingList(page);
        mav.addObject("paging",paging);
        mav.setViewName("/boards/board_list");
        return mav;
    }

    @GetMapping("/view/{boardId}") //게시글 상세조회
    public ModelAndView findById(@PathVariable Long boardId,ModelAndView mav,Principal principal){ //게시글의 번호(Id)값을 인자로 받음
        Board board=boardService.details(boardId);
        mav.addObject("boarddetail",boardService.details(boardId));
        mav.addObject("comments",commentService.list(boardId));
        boardService.updateView(boardId);
        if(principal.getName().equals(board.getUser().getEmail())){ //글을 작성한 user와 로그인한 사림이 일치한경우
            mav.setViewName("/boards/board_details");
            return mav;
        }
        else{
            mav.setViewName("/boards/notuser_writeboard_details");
            return mav;
        }
    }

    @GetMapping("/delete-board")
    public ModelAndView deleteBoarding(Long boardId,ModelAndView mav){
        boardService.deleteById(boardId);
        mav.addObject("data", new Message("게시글이 삭제되었습니다.", "/board"));
        mav.setViewName("Message");
        return mav;
    }

    @PostMapping("/update-board")
    public ModelAndView boardUpdate(Long boardId,Board board,ModelAndView mav) {
        boardService.updateBoard(board,boardId);
        mav.addObject("data", new Message("게시글이 수정되었습니다.", "/board"));
        mav.setViewName("Message");
        return mav;
    }

    @GetMapping("/update")
    public ModelAndView boardUpdateForm(Long boardId,ModelAndView mav) {
        mav.addObject("boarddetail",boardService.details(boardId));
        mav.setViewName("/boards/board_update");
        return mav;
    }
}