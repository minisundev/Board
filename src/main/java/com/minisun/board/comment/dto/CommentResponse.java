package com.minisun.board.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentResponse {

    private Long id;
    private Long modifiedAt;
    private String content;
}
