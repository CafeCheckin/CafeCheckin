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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final Board board;
    private final BoardRepository boardRepository;
    private final UserService userService;

    @GetMapping("/boarding")
    public String boardMain() {
        return "boarding";
    }

    @PostMapping("/boardlist")
    public void boardList(Board board, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        boardService.write(board, userDto);
    }

    @GetMapping("/board")
    public String list(Model model, Principal details) {
        List<Board> boardList = boardRepository.findAll();
        while(!boardList.isEmpty()){
            boardList.
        }
        model.addAttribute("li", boardService.boardingList());
        return "BoardList";
    }
}
