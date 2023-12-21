package com.minisun.board.post.service;

import com.minisun.board.post.dto.PostRequest;
import com.minisun.board.post.dto.PostResponse;
import com.minisun.board.post.entity.Post;
import com.minisun.board.user.entity.User;

import java.util.List;

public interface PostService {

    /*
    * Create Post
    * @param PostRequest : post creation request dto
    * @param User : the author
    * @return : PostResponse
    * */
    public PostResponse createPost(PostRequest request, User user);

    /*
     * Read Post
     * @param postId : post id
     * @return : PostResponse
     * */
    public PostResponse getPost(Long postId);

    /*
     * Read Posts
     * @return : List<PostResponse>
     * */
    public List<PostResponse> getPosts();

    /*
     * Update Post
     * @param postId : id of a post to delete the post
     * @param PostRequest : post update request dto
     * @param User : the author
     * @return : PostResponse
     * */
    public PostResponse updatePost(Long postId, PostRequest request, User user);
}
