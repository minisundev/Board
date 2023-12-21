package com.minisun.board.post.controller;

import com.minisun.board.global.dto.ApiResponse;
import com.minisun.board.global.security.UserDetailsImpl;
import com.minisun.board.post.dto.PostRequest;
import com.minisun.board.post.dto.PostResponse;
import com.minisun.board.post.service.PostService;
import com.minisun.board.post.service.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RequestMapping("/api/posts")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostServiceImpl postServiceImpl) {
        this.postService = postServiceImpl;
    }

    @PostMapping("")
    public ApiResponse<PostResponse> postPost(
            @Valid @RequestBody PostRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            BindingResult bindingResult
            )
    {
        validateRequest(bindingResult);
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

    @PatchMapping("/{postId}")
    public ApiResponse<PostResponse> updatePost(
            @PathVariable(name = "postId") Long postId,
            @Valid @RequestBody PostRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            BindingResult bindingResult
    )
    {
        validateRequest(bindingResult);
        return new ApiResponse<>(HttpStatus.OK.value(),"successfully updated a post",postService.updatePost(postId,request,userDetails.getUser()));
    }

    private void validateRequest(BindingResult bindingResult)
    {
        // Validation exception
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " field : " + fieldError.getDefaultMessage());
            }
            throw new IllegalArgumentException("the input content has exceeded the limit");
        }
    }
}
