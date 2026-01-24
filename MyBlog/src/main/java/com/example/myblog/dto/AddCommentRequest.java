package com.example.myblog.dto;

import com.example.myblog.entity.Board;
import com.example.myblog.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddCommentRequest {
    private String author;
    private String content;

    public Comment toEntity(Board board) {
        return Comment.builder()
                .author(author)
                .content(content)
                .board(board)
                .build();
    }
}
