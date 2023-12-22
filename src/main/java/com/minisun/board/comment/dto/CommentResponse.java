package com.minisun.board.comment.dto;

import com.minisun.board.comment.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentResponse {

    private Long id;
    private LocalDateTime updatedAt;
    private String content;

    public CommentResponse(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.updatedAt = comment.getUpdatedAt();

    }
}
