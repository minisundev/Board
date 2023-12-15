package com.minisun.board.post.entity;

import com.minisun.board.global.entity.Timestamped;
import com.minisun.board.post.dto.PostRequest;
import com.minisun.board.user.entity.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostRequest request, User user){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.user = user;
    }

}
