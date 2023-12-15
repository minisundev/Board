package com.minisun.board.post.repository;

import com.minisun.board.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<List<Post>> findAllByOrderByCreatedAtDesc();
}
