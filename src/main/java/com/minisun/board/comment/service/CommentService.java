package com.minisun.board.comment.service;

import com.minisun.board.comment.dto.CommentRequest;
import com.minisun.board.comment.dto.CommentResponse;
import com.minisun.board.user.entity.User;

import java.util.List;

public interface CommentService {

    /*
     * Create Comment
     * @param Long : the id of the post which the comment will be attached
     * @param CommentRequest : comment creation request dto
     * @param User : the author
     * @return : CommentResponse
     * */
    public CommentResponse createComment(Long postId, CommentRequest request, User user);

    /*
     * Read Comment
     * @param Long : the id of the post that the comments are attached to
     * @return : List of CommentResponse
     * */
    public List<CommentResponse> getComments(Long postId);

    /*
     * Update Comment
     * @param Long : the id of the comment to update
     * @param CommentRequest : comment update request dto
     * @param User : the author
     * @return : CommentResponse
     * */
    public CommentResponse updateComment(Long commentId,CommentRequest request,User user);

    /*
     * Delete Comment
     * @param Long : the id of the comment to delete
     * @return : void
     * */
    public void deleteComment(Long commentId,User user);
}
