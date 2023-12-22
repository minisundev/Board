package com.minisun.board.comment.repository;

import com.minisun.board.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
