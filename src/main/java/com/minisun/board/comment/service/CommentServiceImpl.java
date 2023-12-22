package com.minisun.board.comment.service;

import com.minisun.board.comment.dto.CommentRequest;
import com.minisun.board.comment.dto.CommentResponse;
import com.minisun.board.comment.entity.Comment;
import com.minisun.board.comment.repository.CommentRepository;
import com.minisun.board.post.entity.Post;
import com.minisun.board.post.repository.PostRepository;
import com.minisun.board.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponse createComment(Long postId, CommentRequest request, User user){
        Post post = postRepository.findById(postId).orElseThrow(()-> new NoSuchElementException("the post id doesn't exist"));
        Comment comment =  new Comment(post,request,user);
        Comment saved = commentRepository.save(comment);
        return new CommentResponse(saved);
    }


}
