package com.minisun.board.post.dto;

import com.minisun.board.post.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class PostResponse {

    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private String content;
    private String username;

    public PostResponse(Post post){
        this.id = post.getId();
        this.createdAt = post.getCreatedAt();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
    }

}
