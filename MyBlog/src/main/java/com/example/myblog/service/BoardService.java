package com.example.myblog.service;

import com.example.myblog.dto.AddArticleRequest;
import com.example.myblog.dto.UpdateArticleRequest;
import com.example.myblog.entity.Board;
import com.example.myblog.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    // 자동으로 생성자가 만들어지고
    // 만들어진 생성자를 보고 스프링에서 바로 의존성 주입을 해줌
    // final은 객체가 한 번 의존성 주입되고 변하지 않는게 안전하기 때문
    private final BoardRepository boardRepository;

    // 1. 게시글 추가
    public Board save(AddArticleRequest request) {
        return boardRepository.save(request.toEntity());
    }

    // 2. 게시글 전체 목록 조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // 3. 게시글 상세 조회
    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "not found : " + id
                ));
    }

    // 4. 게시글 삭제
    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    // 5. 게시글 업데이트
    @Transactional
    public Board update(Long id, UpdateArticleRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "not found" + id
                ));

        board.update(request.getTitle(), request.getContent());
        return board;
    }
}
