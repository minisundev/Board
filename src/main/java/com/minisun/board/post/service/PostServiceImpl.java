package com.minisun.board.post.service;

import com.minisun.board.post.dto.PostRequest;
import com.minisun.board.post.dto.PostResponse;
import com.minisun.board.post.entity.Post;
import com.minisun.board.post.repository.PostRepository;
import com.minisun.board.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostResponse createPost(PostRequest request, User user){
        Post post =  new Post(request,user);
        Post saved = postRepository.save(post);
        return new PostResponse(saved);
    }
}
