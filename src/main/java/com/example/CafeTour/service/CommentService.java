package com.example.CafeTour.service;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.Comment;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.CommentRequestDto;
import com.example.CafeTour.dto.CommentResponseDto;
import com.example.CafeTour.dto.CommentUpdateRequestDto;
import com.example.CafeTour.repository.BoardRepository;
import com.example.CafeTour.repository.CommentRepository;
import com.example.CafeTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Comment> list(Long boardId) {
        return commentRepository.findByBoardIdOrderByCreateDateDesc(boardId);
    }

    @Transactional
    public Long write(Long boardId, CommentRequestDto requestDto, String email) {
        User user=userRepository.findByEmail(email).orElseThrow(()
                ->new IllegalArgumentException("존재하지 않는 사용자"));
        Board board=boardRepository.findById(boardId).orElseThrow(()
                ->new IllegalArgumentException("존재하지 않는 게시판"));
        return commentRepository.save(requestDto.toEntity(user,board)).getId();
    }

    @Transactional
    public CommentResponseDto detail(Long commentId) {
        Comment comment=isError(commentId);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public void update(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment persistence=isError(commentId);
       persistence.update(requestDto);
    }

    @Transactional
    public void deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment isError(Long commentId){
        Comment persistence=commentRepository.findById(commentId).orElseThrow(()
                ->new IllegalArgumentException("존재하지 않는 댓글"));
        return persistence;
    }
}
