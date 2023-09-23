package com.example.CafeTour.comment;

import com.example.CafeTour.board.Board;
import com.example.CafeTour.board.BoardService;
import com.example.CafeTour.user.User;
import com.example.CafeTour.comment.commentdto.CommentRequestDto;
import com.example.CafeTour.comment.commentdto.CommentResponseDto;
import com.example.CafeTour.comment.commentdto.CommentUpdateRequestDto;
import com.example.CafeTour.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final BoardService boardService;

    @Transactional(readOnly = true)
    public List<Comment> list(Long boardId) {
        return commentRepository.findByBoardIdOrderByCreateDateDesc(boardId);
    }

    public Long write(Long boardId, CommentRequestDto requestDto, String email) {
        User user=userService.isError(email);
        Board board=boardService.isError(boardId);
        return commentRepository.save(requestDto.toEntity(user,board)).getId();
    }

    public CommentResponseDto detail(Long commentId) {
        Comment comment=isError(commentId);
        return new CommentResponseDto(comment);
    }

    public void update(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment persistence=isError(commentId);
       persistence.update(requestDto);
    }

    public Comment isError(Long commentId){
        Comment persistence=commentRepository.findById(commentId).orElseThrow(()
                ->new IllegalArgumentException("존재하지 않는 댓글"));
        return persistence;
    }
}
