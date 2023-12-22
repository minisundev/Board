package com.minisun.board.post.controller;

import com.minisun.board.comment.dto.CommentResponse;
import com.minisun.board.comment.service.CommentService;
import com.minisun.board.comment.service.CommentServiceImpl;
import com.minisun.board.global.dto.ApiResponse;
import com.minisun.board.global.dto.Data2;
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
    private final CommentService commentService;

    public PostController(PostServiceImpl postService, CommentServiceImpl commentService) {
        this.postService = postService;
        this.commentService = commentService;
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
    public ApiResponse<Data2<PostResponse,List<CommentResponse>>> getPost(
            @PathVariable(name = "postId") Long postId
    )
    {
        return new ApiResponse<>(HttpStatus.OK.value(),"successfully read a post",new Data2(postService.getPost(postId),commentService.getComments(postId)));
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

    @DeleteMapping("/{postId}")
    public ApiResponse<Void> deletePost(
            @PathVariable(name = "postId") Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    )
    {
        postService.deletePost(postId,userDetails.getUser());
        return new ApiResponse<>(HttpStatus.OK.value(),"successfully deleted a post");
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
