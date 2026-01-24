package com.example.myblog.repository;

import com.example.myblog.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속받는거 만으로 많은 기능이 가능
public interface BoardRepository extends JpaRepository<Board, Long> {
}
