package com.minisun.board.comment.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRequest {

    @Size(max = 5000, message ="comment should be shorter than 5000")
    private String comment;
}
