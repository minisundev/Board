package com.minisun.board.comment.controller;

import com.minisun.board.comment.service.CommentService;
import com.minisun.board.comment.service.CommentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentServiceImpl commentServiceImpl){
        this.commentService = commentServiceImpl;
    }

}
