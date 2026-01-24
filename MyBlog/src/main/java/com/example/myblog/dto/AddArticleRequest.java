package com.example.myblog.dto;

import com.example.myblog.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddArticleRequest {

    private String title;
    private String content;
    private String author;

    // dto를 엔티티로 변환하는 메서드
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
