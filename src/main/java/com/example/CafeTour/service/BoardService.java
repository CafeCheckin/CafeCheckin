package com.example.CafeTour.service;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.dto.BoardDto;
import com.example.CafeTour.repository.BoardRepository;
import com.example.CafeTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Page<Board> getBoardList(PageRequest pageRequest){
        return boardRepository.findAll(pageRequest);
    }

    public void write(Board board){
        boardRepository.save(board);
    }
}
