package com.example.CafeTour.controller;

import com.example.CafeTour.auth.PrincipalDetails;
import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.BoardDto;
import com.example.CafeTour.repository.BoardRepository;
import com.example.CafeTour.service.BoardService;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final UserService userService;

    @GetMapping("/boarding") //글 작성
    public String boardMain() {
        return "boarding";
    }

    @PostMapping("/writeboard") //작성한 게시글 저장
    public String boardList(Board board, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        boardService.write(board, userDto);
        return "redirect:/board";
    }

    @GetMapping("/board")  //게시글 목록
    public String list(Model model, Principal details) {
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("li", boardService.boardingList());
        return "BoardList";
    }

    @GetMapping("/view") //게시글 상세조회
    public String findById(Long id,Model model){ //게시글의 번호(Id)값을 인자로 받음
        model.addAttribute("boarddetail",boardService.details(id));
        //System.out.println(boardRepository.findByNickname(id));
        return "BoardDetails";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoarding(Long id){
        boardService.deleteById(id);
        return "redirect:/board";
    }
}