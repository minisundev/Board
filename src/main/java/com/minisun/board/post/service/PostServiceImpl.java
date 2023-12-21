package com.minisun.board.post.service;

import com.minisun.board.post.dto.PostRequest;
import com.minisun.board.post.dto.PostResponse;
import com.minisun.board.post.entity.Post;
import com.minisun.board.post.repository.PostRepository;
import com.minisun.board.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostResponse createPost(PostRequest request, User user){
        Post post =  new Post(request,user);
        Post saved = postRepository.save(post);
        return new PostResponse(saved);
    }

    public PostResponse getPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new NullPointerException("the post id doesn't exist"));
        return new PostResponse(post);
    }

    public List<PostResponse> getPosts(){
        return postRepository.findAllByOrderByCreatedAtDesc()
                .orElseThrow(()-> new NullPointerException("there are no posts"))
                .stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponse updatePost(Long postId, PostRequest request, User user){
        Post post = getUserPost(postId,user);
        post.update(request);
        return new PostResponse(post);
    }

    public void deletePost(Long postId, User user){
        Post post = getUserPost(postId,user);
        postRepository.delete(post);
    }

    private Post getUserPost(Long postId, User user){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new NullPointerException("the post id doesn't exist"));

        if(!post.getUser().getId().equals(user.getId())){
            throw new RejectedExecutionException("only the author can modify/delete the post");
        }
        return post;
    }
}
