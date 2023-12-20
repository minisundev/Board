package com.minisun.board.post.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequest {

    @Size(max = 500, message ="title should be shorter than 500")
    private String title;

    @Size(max = 5000, message ="content should be shorter than 5000")
    private String content;

}
