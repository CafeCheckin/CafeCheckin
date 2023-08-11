package com.example.CafeTour.service;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.BoardDto;
import com.example.CafeTour.repository.BoardRepository;
import com.example.CafeTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void write(Board board, User user){
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public List<Board>  boardingList(){
        return boardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Board details(Long id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패");
                });
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(Board board,Long id) {
        Board persistance=boardRepository.findById(id).orElseThrow(()
                ->{return new IllegalArgumentException("글 찾기 실패");
        });
        System.out.println(board.getTitle());
        System.out.println(board.getBoardOpinion());
        System.out.println(persistance.getId());
        persistance.setTitle(board.getTitle());
        persistance.setBoardOpinion(board.getBoardOpinion());
        persistance.setModifyDate(board.getModifyDate());
        System.out.println(board.getModifyDate());
    }
}
