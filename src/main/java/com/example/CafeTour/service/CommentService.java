package com.example.CafeTour.service;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.Comment;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<Comment> list(Long boardId) {
        return commentRepository.findByBoardIdOrderByCreateDateDesc(boardId);
    }

    @Transactional
    public void write(Comment comment, User user, Board details) {
        comment.setUser(user);
        comment.setBoard(details);
        commentRepository.save(comment);
    }

    @Transactional
    public Comment detail(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(()
                ->{return new IllegalArgumentException("댓글 찾기 실패");
                });
    }

    @Transactional
    public void update(Long commentId, Comment comment) {
        Comment persistance=commentRepository.findById(commentId).orElseThrow(()
                -> {
            return new IllegalArgumentException("댓글 수정 실패");
        });
        persistance.setCommentText(comment.getCommentText());
        persistance.setModifyDate(comment.getModifyDate());
    }

    public void deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
