package com.example.myblog.controller;

import com.example.myblog.dto.AddCommentRequest;
import com.example.myblog.dto.UpdateArticleRequest;
import com.example.myblog.dto.UpdateCommentRequest;
import com.example.myblog.service.BoardService;
import com.example.myblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
// 주로 update, delete에 쓰임
public class BoardApiController {
    private final BoardService boardService;
    private final CommentService commentService;

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        boardService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Void> updateArticle(@PathVariable Long id,
                                              @RequestBody UpdateArticleRequest request) {
        boardService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/comments/{articleId}")
    public ResponseEntity<Void> addComment(@PathVariable Long articleId,
                                           @RequestBody AddCommentRequest request) {
        commentService.save(articleId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/comments/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable Long id,
                                              @RequestBody UpdateCommentRequest request) {
        commentService.update(id, request);
        return ResponseEntity.ok().build();
    }
}
