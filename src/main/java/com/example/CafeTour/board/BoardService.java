package com.example.CafeTour.board;

import com.example.CafeTour.comment.Comment;
import com.example.CafeTour.user.User;
import com.example.CafeTour.board.boarddto.BoardRequestDto;
import com.example.CafeTour.board.boarddto.BoardResponseDto;
import com.example.CafeTour.board.boarddto.BoardUpdateRequestDto;
import com.example.CafeTour.comment.CommentRepository;
import com.example.CafeTour.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long write(BoardRequestDto requestDto,String userEmail){
        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자"));
        return boardRepository.save(requestDto.toEntity(user)).getId();
    } //게시판 글 작성

    @Transactional(readOnly = true)
    public Page<Board> boardingList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable= PageRequest.of(page,10,Sort.by(sorts));
        return this.boardRepository.findAll(pageable);
    } //게시판 목록 출력

    @Transactional(readOnly = true)
    public BoardResponseDto details(Long id){
        Board board= isError(id);
        List<Comment> comments = commentRepository.findByBoardIdOrderByCreateDateDesc(id);
        List<BoardResponseDto.CommentReponseDto> result = comments.stream()
                .map(comment -> new BoardResponseDto.CommentReponseDto(comment))
                .collect(Collectors.toList());
        return new BoardResponseDto(board, result);
    } //게시판 세부사항 조회

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    } //게시글 삭제

    @Transactional
    public void updateBoard(BoardUpdateRequestDto requestDto, Long id) {
        Board persistence=isError(id);
        persistence.update(requestDto);
    } //게시글 수정

    @Transactional
    public void updateView(Long boardId) {
        boardRepository.updateView(boardId);
    }

    public Board isError(Long bookId){
        Board board= boardRepository.findById(bookId)
                .orElseThrow(()-> new IllegalArgumentException("글 찾기 실패"));
        return board;
    }
}