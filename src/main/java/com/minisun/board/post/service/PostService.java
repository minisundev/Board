package com.minisun.board.post.service;

import com.minisun.board.post.dto.PostRequest;
import com.minisun.board.post.dto.PostResponse;
import com.minisun.board.post.entity.Post;
import com.minisun.board.user.entity.User;

public interface PostService {

    /*
    * Create Post
    * @param PostRequest : post creation request dto
    * @param User : the author
    * */
    public PostResponse createPost(PostRequest request, User user);
}
