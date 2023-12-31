package com.minisun.board.comment.controller;

import com.minisun.board.comment.dto.CommentRequest;
import com.minisun.board.comment.dto.CommentResponse;
import com.minisun.board.comment.service.CommentService;
import com.minisun.board.comment.service.CommentServiceImpl;
import com.minisun.board.global.dto.ApiResponse;
import com.minisun.board.global.security.UserDetailsImpl;
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
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentServiceImpl commentServiceImpl){
        this.commentService = commentServiceImpl;
    }

    @PostMapping("/{postId}")
    public ApiResponse<CommentResponse> postComment(
            @PathVariable(name = "postId") Long postId,
            @Valid @RequestBody CommentRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            BindingResult bindingResult
    )
    {
        validateRequest(bindingResult);
        return new ApiResponse<>(HttpStatus.CREATED.value(),"successfully posted",commentService.createComment(postId,request,userDetails.getUser()));
    }

    @GetMapping("/{postId}/comments")
    public ApiResponse<List<CommentResponse>> getComments(
            @PathVariable(name = "postId") Long postId
    )
    {
        return new ApiResponse<>(HttpStatus.OK.value(),"successfully read",commentService.getComments(postId));
    }

    @PatchMapping("/{postId}/{commentId}")
    public ApiResponse<CommentResponse> updateComment(
            @PathVariable(name = "commentId") Long commentId,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    )
    {
        return new ApiResponse<>(HttpStatus.OK.value(),"successfully updated",commentService.updateComment(commentId,request,userDetails.getUser()));
    }

    @DeleteMapping("/{postId}/{commentId}")
    public ApiResponse<List<CommentResponse>> deleteComment(
            @PathVariable(name = "commentId") Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    )
    {
        commentService.deleteComment(commentId,userDetails.getUser());
        return new ApiResponse<>(HttpStatus.OK.value(),"successfully deleted");
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
