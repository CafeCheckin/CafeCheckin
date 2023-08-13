package com.example.CafeTour.service;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
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
    } //게시판 글 작성

    @Transactional(readOnly = true)
    public List<Board>  boardingList(){
        return boardRepository.findAll();
    } //게시판 목록 출력

    @Transactional(readOnly = true)
    public Board details(Long id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패");
                });
    } //게시판 세부사항 조회

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    } //게시글 삭제

    @Transactional
    public void updateBoard(Board board,Long id) {
        Board persistance=boardRepository.findById(id).orElseThrow(()
                ->{return new IllegalArgumentException("글 찾기 실패");
        });
        persistance.setTitle(board.getTitle());
        persistance.setBoardOpinion(board.getBoardOpinion());
        persistance.setModifyDate(board.getModifyDate());
        System.out.println(board.getModifyDate());
    } //게시글 수정
}
