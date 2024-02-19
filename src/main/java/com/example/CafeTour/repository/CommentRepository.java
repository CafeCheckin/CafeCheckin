package com.example.CafeTour.repository;

import com.example.CafeTour.domain.CafeReview;
import com.example.CafeTour.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBoardIdOrderByCreateDateDesc(Long boardId);
}
