package com.example.CafeTour.board;

import com.example.CafeTour.board.boarddto.BoardRequestDto;
import com.example.CafeTour.board.boarddto.BoardResponseDto;
import com.example.CafeTour.board.boarddto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/write-board") //작성한 게시글 저장
    public Long boardList(@RequestBody BoardRequestDto requestDto,Principal principal) {
        return boardService.write(requestDto, principal.getName());
    }

    @GetMapping("/board")  //게시글 목록
    public ModelAndView list(ModelAndView mav, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Board> paging=this.boardService.boardingList(page);
        mav.addObject("paging",paging);
        mav.setViewName("/boards/board_list");
        return mav;
    }

    @GetMapping("/view/{boardId}") //게시글 상세조회
    public BoardResponseDto findById(@PathVariable Long boardId,Principal principal){ //게시글의 번호(Id)값을 인자로 받음
        return boardService.details(boardId);
    }

    @DeleteMapping("/board/{boardId}") //게시글 삭제
    public String deleteBoarding(@PathVariable Long boardId){
        boardService.deleteById(boardId);
        return "삭제 완료";
    }

    @PatchMapping("/board/{boardId}")
    public Long boardUpdate(@PathVariable Long boardId,@RequestBody BoardUpdateRequestDto requestDto) {
         boardService.updateBoard(requestDto,boardId);
         return boardId;
    }
}