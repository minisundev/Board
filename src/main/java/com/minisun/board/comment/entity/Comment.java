package com.minisun.board.comment.entity;

import com.minisun.board.comment.dto.CommentRequest;
import com.minisun.board.global.entity.Timestamped;
import com.minisun.board.post.entity.Post;
import com.minisun.board.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(length = 5000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(Post post, CommentRequest request, User user){
        this.post = post;
        this.content = request.getComment();
        this.user = user;
    }

}
