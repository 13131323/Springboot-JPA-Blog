package com.example.myblog.repository;

import com.example.myblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글 ID로 모든 댓글 조회
    // find + All + By + Board + Id 이 형식으로 이름을 만들어서
    // 스프링이 자동으로 이걸 사용할 수 있게해줌. sql을 날리는 형태로
    List<Comment> findAllByBoardId(Long boardId);
}