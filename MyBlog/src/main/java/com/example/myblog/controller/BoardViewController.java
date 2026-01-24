package com.example.myblog.controller;

import com.example.myblog.dto.AddArticleRequest;
import com.example.myblog.entity.Board;
import com.example.myblog.entity.Comment;
import com.example.myblog.repository.CommentRepository;
import com.example.myblog.service.BoardService;
import com.example.myblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardViewController {
    private final BoardService boardService;
    private final CommentService commentService;

    // 게시글 목록 조회
    @GetMapping("/articles")
    public String getArticles(Model model) {
        // 1. 서비스에서 모든 게시글 가져오기
        List<Board> articles = boardService.findAll();

        // 2. 모델에 담아서 html로 전달
        model.addAttribute("articles", articles);

        // 3. templates/articleList.html 파일을 찾아서 보여줌
        return "articleList";
    }

    // 글쓰기 화면
    @GetMapping("/new-article")
    public String newArticle() {
        return "newArticle";
    }

    // 글 저장 요청 처리
    @PostMapping("/articles")
    public String addArticle(AddArticleRequest request) {
        boardService.save(request);
        return "redirect:/articles";
    }

    // 특정 게시글 상세보기
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        // 1. 게시글이랑 댓글 목록 찾기
        Board article = boardService.findById(id);
        List<Comment> comments = commentService.findAllByArticleId(id);

        // 2. 찾은 게시글을 모델에 담아 화면으로 전달
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);

        // 3. 상세 페이지 html 변환
        return "articleDetail";
    }
}
