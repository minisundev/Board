package com.minisun.board.post.controller;

import com.minisun.board.global.dto.ApiResponse;
import com.minisun.board.global.security.UserDetailsImpl;
import com.minisun.board.post.dto.PostRequest;
import com.minisun.board.post.dto.PostResponse;
import com.minisun.board.post.service.PostService;
import com.minisun.board.post.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostServiceImpl postServiceImpl) {
        this.postService = postServiceImpl;
    }

    @PostMapping("")
    public ApiResponse<PostResponse> postPost(
            @RequestBody PostRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            )
    {
        return new ApiResponse<>(HttpStatus.CREATED.value(),"successfully posted",postService.createPost(request,userDetails.getUser()));
    }

    @GetMapping("/read/{postId}")
    public ApiResponse<PostResponse> getPost(
            @PathVariable Long postId
    )
    {
        return new ApiResponse<>(HttpStatus.OK.value(),"successfully read a post",postService.getPost(postId));
    }

    @GetMapping("/read")
    public ApiResponse<List<PostResponse>> getPosts(
    )
    {
        return new ApiResponse<>(HttpStatus.OK.value(),"successfully read all posts",postService.getPosts());
    }
}
