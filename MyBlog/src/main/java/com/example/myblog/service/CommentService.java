package com.example.myblog.service;

import com.example.myblog.dto.AddCommentRequest;
import com.example.myblog.dto.UpdateCommentRequest;
import com.example.myblog.entity.Board;
import com.example.myblog.entity.Comment;
import com.example.myblog.repository.BoardRepository;
import com.example.myblog.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 특정 게시글의 모든 댓글 조회
    public List<Comment> findAllByArticleId(Long articleId) {
        return commentRepository.findAllByBoardId(articleId);
    }

    // 댓글 쓰기
    public Comment save(Long articleId, AddCommentRequest request) {
        Board board = boardRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "not found : " + articleId
                ));

        board.addComment();
        return commentRepository.save(request.toEntity(board));
    }

    // 댓글 삭제
    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "not found"
                        ));

        comment.getBoard().removeComment();
        commentRepository.delete(comment);
    }

    // 댓글 수정
    @Transactional
    public void update(Long id, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "not found : " + id
                ));
        comment.update(request.getContent());
    }
}
