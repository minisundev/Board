package com.minisun.board.comment.service;

import com.minisun.board.comment.dto.CommentRequest;
import com.minisun.board.comment.dto.CommentResponse;
import com.minisun.board.comment.entity.Comment;
import com.minisun.board.comment.repository.CommentRepository;
import com.minisun.board.post.entity.Post;
import com.minisun.board.post.repository.PostRepository;
import com.minisun.board.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

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

    public List<CommentResponse> getComments(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()-> new NoSuchElementException("the post id doesn't exist"));
        List<Comment> comments =  post.getComments();
        return comments.stream().map(CommentResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse updateComment(Long commentId,CommentRequest request,User user){
        Comment comment = getUserComment(commentId,user);
        comment.update(request);
        return new CommentResponse(comment);
    }

    public void deleteComment(Long commentId,User user){
        Comment comment  = getUserComment(commentId,user);
        commentRepository.delete(comment);
    }

    private Comment getUserComment(Long commentId,User user){
        Comment comment =  commentRepository.findById(commentId).orElseThrow(()-> new NoSuchElementException("the comment id doesn't exist"));
        if(!comment.getUser().getId().equals(user.getId())){
            throw new RejectedExecutionException("only the author can modify/delete");
        }
        return comment;
    }

}
