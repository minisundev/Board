package com.minisun.board.comment.service;

import com.minisun.board.comment.dto.CommentResponse;
import com.minisun.board.comment.repository.CommentRepository;
import com.minisun.board.post.entity.Post;
import com.minisun.board.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;


}
