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

    public void write(Board board, User user){
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional
    public List<Board>  boardingList(){
        return boardRepository.findAll();
    }

    public Board details(Long id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패");
                });
    }
}
