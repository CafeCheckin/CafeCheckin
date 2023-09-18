package com.example.CafeTour.controller;

import com.example.CafeTour.dto.CommentRequestDto;
import com.example.CafeTour.dto.CommentResponseDto;
import com.example.CafeTour.dto.CommentUpdateRequestDto;
import com.example.CafeTour.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment-write") //게시판 댓글 작성
    public Long commentWrite(Long boardId, @RequestBody CommentRequestDto requestDto, Principal principal){
        return commentService.write(boardId,requestDto,principal.getName());
    }

    @GetMapping("/comment-detail/{commentId}") //게시판 댓글 상세조회
    public CommentResponseDto commentDetail(@PathVariable Long commentId, Long boardId){
        return commentService.detail(commentId);
    }

    @PatchMapping("/update-comment/{commentId}") //게시판 댓글 수정
    public Long updateComment(@PathVariable Long commentId,@RequestBody CommentUpdateRequestDto requestDto){
        commentService.update(commentId,requestDto);
        return commentId;
    }

    @DeleteMapping("/comment-delete") //댓글 삭제
    public String commentDelete(Long commentId){
        commentService.deleteById(commentId);
        return "삭제 완료";
    }
}
