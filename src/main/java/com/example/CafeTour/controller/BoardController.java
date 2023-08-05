package com.example.CafeTour.controller;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.repository.BoardRepository;
import com.example.CafeTour.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final Board board;
    private final BoardRepository boardRepository;

    @GetMapping("/boarding")
    public String boardMain(){
        return "boarding";
    }

    @PostMapping("/boardlist")
    public void boardList(@RequestParam  String title,String boardOpinion){
        board.setTitle(title);
        board.setBoardOpinion(boardOpinion);
        boardService.write(board);
    }

    @GetMapping("/board")
    public String list(Model model){
        List<Board> boardList=boardRepository.findAll();
        model.addAttribute("li",boardList);
        return "BoardList";
    }
}
