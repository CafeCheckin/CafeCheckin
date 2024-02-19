package com.example.CafeTour.controller;

import com.example.CafeTour.Message;
import com.example.CafeTour.domain.Comment;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.service.BoardService;
import com.example.CafeTour.service.CommentService;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final BoardService boardService;

    @GetMapping("/write-comment")
    public ModelAndView writeComment(Long boardId, ModelAndView mav){
        mav.addObject("boardId",boardId);
        mav.setViewName("/comments/write_comment");
        return mav;
    }

    @PostMapping("/comment-write") //게시판 댓글 작성
    public ModelAndView commentWrite(Long boardId, Comment comment,Principal principal,ModelAndView mav){
        User user=userService.findByEmail(principal.getName());
        commentService.write(comment,user,boardService.details(boardId));
        mav.addObject("data", new Message("댓글이 작성되었습니다.", "/board"));
        mav.setViewName("Message");
        return mav;
    }

    @GetMapping("/comment-detail")
    public ModelAndView commentDetail(Long commentId,Long boardId ,ModelAndView mav,Principal principal){
        Comment comment=commentService.detail(commentId);
        mav.addObject("commentdetail",comment);
        mav.addObject("boardId",boardId);
        if(principal.getName().equals(comment.getUser().getEmail())){
            mav.setViewName("/comments/comment_detail");
            return mav;
        }
        else{
            mav.setViewName("/comments/not_comment_detail");
            return mav;
        }
    }

    @GetMapping("/comment-update")
    public ModelAndView commentUpdate(Long commentId,Long boardId,ModelAndView mav){
        mav.addObject("commentupdate",commentService.detail(commentId));
        mav.addObject("boardId",boardId);
        mav.setViewName("/comments/comment_update_form");
        return mav;
    }

    @PostMapping("/update-comment")
    public ModelAndView updateComment(Long commentId,Long boardId,Comment comment,ModelAndView mav){
        commentService.update(commentId,comment);
        mav.addObject("data", new Message("댓글이 수정되었습니다.", "/view/"+boardId));
        mav.setViewName("Message");
        return mav;
    }

    @GetMapping("/comment-delete") //댓글 삭제
    public ModelAndView commentDelete(Long commentId,ModelAndView mav){
        commentService.deleteById(commentId);
        mav.addObject("data", new Message("댓글이 삭제되었습니다.", "/view"));
        mav.setViewName("Message");
        return mav;
    }
}
