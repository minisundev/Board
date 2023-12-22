package com.minisun.board.comment.service;

import com.minisun.board.comment.dto.CommentRequest;
import com.minisun.board.comment.dto.CommentResponse;
import com.minisun.board.user.entity.User;

public interface CommentService {

    /*
     * Create Comment
     * @param Long : the id of the post which the comment will be attached
     * @param CommentRequest : comment creation request dto
     * @param User : the author
     * @return : CommentResponse
     * */
    public CommentResponse createComment(Long postId, CommentRequest request, User user);
}
