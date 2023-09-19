package com.example.CafeTour.comment;

import com.example.CafeTour.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBoardIdOrderByCreateDateDesc(Long boardId);
}
